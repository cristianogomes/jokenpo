package com.jokenpo.game.controller;

import com.jokenpo.game.model.Action;
import com.jokenpo.game.model.Player;
import com.jokenpo.game.model.Tool;
import com.jokenpo.game.repository.PlayerRepository;
import com.jokenpo.game.repository.ToolRepository;
import com.jokenpo.game.service.ToolService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerRepository playerRepositoryMock;

    @MockBean
    private ToolRepository toolRepositoryMock;

    @Before
    public void init() {
        Player p1 = new Player();
        p1.setId(1L);
        p1.setName("Nome Player 1");

        Player p2 = new Player();
        p2.setId(2L);
        p2.setName("Nome Player 2");

        when(playerRepositoryMock.findById(1L)).thenReturn(Optional.of(p1));
        when(playerRepositoryMock.findById(2L)).thenReturn(Optional.of(p2));

        List<Tool> tools = getTools();
        when(toolRepositoryMock.findById(1L)).thenReturn(Optional.of(tools.get(0))); //pedra
        when(toolRepositoryMock.findById(3L)).thenReturn(Optional.of(tools.get(2))); //tesoura
    }

    @Test
    public void postMove() throws Exception {
        mockMvc.perform(get("/jokenpo/reset"));

        mockMvc.perform(post("/jokenpo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"playerId\": \"1\", \"toolId\": \"1\"}")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$", Matchers.hasKey("data")));

        verify(playerRepositoryMock, times(1)).findById(any());
        verify(toolRepositoryMock, times(1)).findById(any());
    }

    @Test
    public void getWinner() throws Exception {
        mockMvc.perform(get("/jokenpo/reset"));

        //POST jogador 1
        mockMvc.perform(post("/jokenpo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"playerId\": \"1\", \"toolId\": \"1\"}")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$", Matchers.hasKey("data")));

        //POST jogador 2
        mockMvc.perform(post("/jokenpo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"playerId\": \"2\", \"toolId\": \"3\"}")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$", Matchers.hasKey("data")));

        //GET resultado
        mockMvc.perform(get ("/jokenpo/result"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data[0].player.id", Matchers.is(1))) //pontuacao jogador 1
                .andExpect(jsonPath("$.data[0].score", Matchers.is(1))) //pontuacao jogador 1
                .andExpect(jsonPath("$.data[1].player.id", Matchers.is(2))) //pontuacao jogador 2
                .andExpect(jsonPath("$.data[1].score", Matchers.is(0)));//pontuacao jogador 2

        mockMvc.perform(get("/reset"));

        verify(playerRepositoryMock, times(2)).findById(any());
        verify(toolRepositoryMock, times(2)).findById(any());
    }


    private List<Tool> getTools() {
        Tool pedra = new Tool();
        pedra.setId(1L);
        pedra.setName("pedra");

        Tool papel = new Tool();
        papel.setId(2L);
        papel.setName("papel");

        Tool tesoura = new Tool();
        tesoura.setId(3L);
        tesoura.setName("tesoura");

        Action pedraTesoura = new Action();
        pedraTesoura.setId(1L);
        pedraTesoura.setSource(pedra);
        pedraTesoura.setTarget(tesoura);
        pedraTesoura.setDescription("qubra");
        pedra.setActions(Arrays.asList(pedraTesoura));

        Action tesouraPapel = new Action();
        tesouraPapel.setId(2L);
        tesouraPapel.setSource(tesoura);
        tesouraPapel.setTarget(papel);
        tesouraPapel.setDescription("corta");
        tesoura.setActions(Arrays.asList(tesouraPapel));

        Action papelPedra = new Action();
        papelPedra.setId(3L);
        papelPedra.setSource(papel);
        papelPedra.setTarget(pedra);
        papelPedra.setDescription("cobre");
        papel.setActions(Arrays.asList(papelPedra));

        return Arrays.asList(pedra, papel, tesoura);
    }
}


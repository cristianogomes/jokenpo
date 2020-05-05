package com.jokenpo.game.controller;

import com.jokenpo.game.model.Player;
import com.jokenpo.game.repository.PlayerRepository;
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
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerRepository playerRepositoryMock;

    @Before
    public void init() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Nome Player");
        when(playerRepositoryMock.findById(1L)).thenReturn(Optional.of(player));
        when(playerRepositoryMock.save(any())).thenReturn(player);
        when(playerRepositoryMock.findAll()).thenReturn(Arrays.asList(player, player));
    }

    @Test
    public void getPlayerById() throws Exception {
        mockMvc.perform(get ("/player/{id}", 1))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data[0].id", Matchers.is(1)));

        verify(playerRepositoryMock, times(1)).findById(1L);
    }

    @Test
    public void getPlayerById_NotFound() throws Exception {
        mockMvc.perform(get ("/player/{id}", 0))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.NOT_FOUND.value())))
                .andExpect(jsonPath("$", Matchers.hasKey("error")));

        verify(playerRepositoryMock, times(1)).findById(0L);
    }

    @Test
    public void getAllPlayers() throws Exception {
        mockMvc.perform(get ("/player"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data", Matchers.hasSize(2)));

        verify(playerRepositoryMock, times(1)).findAll();
    }

    @Test
    public void createPlayer() throws Exception {
        mockMvc.perform(post("/player")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"player name\"}")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$", Matchers.hasKey("data")));

        verify(playerRepositoryMock, times(1)).save(any());
    }

}

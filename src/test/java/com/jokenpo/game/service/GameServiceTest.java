package com.jokenpo.game.service;

import com.jokenpo.game.dto.MoveDTO;
import com.jokenpo.game.exception.MoveAlreadyExistsException;
import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Action;
import com.jokenpo.game.model.Player;
import com.jokenpo.game.model.Result;
import com.jokenpo.game.model.Tool;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceTest {

    @Mock
    private PlayerService playerServiceMock;

    @Mock
    private ToolService toolServiceMock;

    @InjectMocks
    private GameService gameService;

    @Before
    public void clearGame() {
        this.gameService.clear();
    }

    @Test(expected = NotFoundException.class)
    public void testDoMove_InvalidUser() throws NotFoundException, MoveAlreadyExistsException {
        when(this.playerServiceMock.findById(1L)).thenThrow(new NotFoundException());
        when(this.toolServiceMock.findById(1L)).thenReturn(new Tool());

        MoveDTO moveDTO = new MoveDTO();
        moveDTO.setPlayerId(1l);

        this.gameService.doMove(moveDTO);
    }

    @Test(expected = NotFoundException.class)
    public void testDoMove_InvalidTool() throws NotFoundException, MoveAlreadyExistsException {
        when(this.playerServiceMock.findById(1L)).thenReturn(new Player());
        when(this.toolServiceMock.findById(1L)).thenThrow(new NotFoundException());

        MoveDTO moveDTO = new MoveDTO();
        moveDTO.setToolId(1l);

        this.gameService.doMove(moveDTO);
    }

    @Test(expected = MoveAlreadyExistsException.class)
    public void testDoMove_AlreadyPlayed() throws NotFoundException, MoveAlreadyExistsException {
        Player p1 = new Player();
        p1.setId(1L);
        p1.setName("Player Name");

        when(this.playerServiceMock.findById(1L)).thenReturn(p1);

        MoveDTO moveDTO = new MoveDTO();
        moveDTO.setPlayerId(1l);

        this.gameService.doMove(moveDTO);
        this.gameService.doMove(moveDTO);
    }

    @Test
    public void testRemoveMove() throws NotFoundException, MoveAlreadyExistsException {
        Player p1 = new Player();
        p1.setId(1L);
        p1.setName("Player Name");

        when(this.playerServiceMock.findById(1L)).thenReturn(p1);

        MoveDTO moveDTO = new MoveDTO();
        moveDTO.setPlayerId(1l);

        this.gameService.doMove(moveDTO);
        this.gameService.removeMove(p1);

        List<Result> result = this.gameService.getResult();
        Assert.assertTrue(result.isEmpty());
    }

    @Test(expected = NotFoundException.class)
    public void testRemoveMove_NotFound() throws NotFoundException {
        this.gameService.removeMove(new Player());
    }

    @Test
    public void testWinner_Player1() throws NotFoundException, MoveAlreadyExistsException {
        Player p1 = new Player();
        p1.setId(1L);
        p1.setName("Player Name 1");

        Player p2 = new Player();
        p2.setId(2L);
        p2.setName("Player Name 2");

        Player p3 = new Player();
        p3.setId(3L);
        p3.setName("Player Name 3");

        List<Tool> tools = getTools();

        when(this.playerServiceMock.findById(1L)).thenReturn(p1);
        when(this.playerServiceMock.findById(2L)).thenReturn(p2);
        when(this.playerServiceMock.findById(3L)).thenReturn(p3);
        when(this.toolServiceMock.findById(1L)).thenReturn(tools.get(0)); //pedra
        when(this.toolServiceMock.findById(2L)).thenReturn(tools.get(2)); //tesoura

        MoveDTO moveP1 = new MoveDTO();
        moveP1.setPlayerId(1l);
        moveP1.setToolId(1L); //pedra

        MoveDTO moveP2 = new MoveDTO();
        moveP2.setPlayerId(2l);
        moveP2.setToolId(2L); //tesoura

        MoveDTO moveP3 = new MoveDTO();
        moveP3.setPlayerId(3l);
        moveP3.setToolId(2L); //tesoura

        this.gameService.doMove(moveP1);
        this.gameService.doMove(moveP2);
        this.gameService.doMove(moveP3);

        List<Result> result = this.gameService.getResult();

        Assert.assertNotNull(result);
        Assert.assertEquals(result.get(0).getScore(), 2);
        Assert.assertEquals(result.get(1).getScore(), 0);
        Assert.assertEquals(result.get(2).getScore(), 0);
    }

    @Test
    public void testWinner_Tie() throws NotFoundException, MoveAlreadyExistsException {
        Player p1 = new Player();
        p1.setId(1L);
        p1.setName("Player Name 1");

        Player p2 = new Player();
        p2.setId(2L);
        p2.setName("Player Name 2");

        List<Tool> tools = getTools();

        when(this.playerServiceMock.findById(1L)).thenReturn(p1);
        when(this.playerServiceMock.findById(2L)).thenReturn(p2);
        when(this.toolServiceMock.findById(1L)).thenReturn(tools.get(0)); //pedra

        MoveDTO moveP1 = new MoveDTO();
        moveP1.setPlayerId(1l);
        moveP1.setToolId(1L); //pedra

        MoveDTO moveP2 = new MoveDTO();
        moveP2.setPlayerId(2l);
        moveP2.setToolId(1L); //pedra

        this.gameService.doMove(moveP1);
        this.gameService.doMove(moveP2);

        List<Result> result = this.gameService.getResult();

        Assert.assertNotNull(result);
        Assert.assertEquals(result.get(0).getScore(), 0);
        Assert.assertEquals(result.get(1).getScore(), 0);
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

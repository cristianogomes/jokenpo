package com.jokenpo.game.service;

import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Player;
import com.jokenpo.game.repository.PlayerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepositoryMock;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void testFindById() throws NotFoundException {
        Player p1 = new Player();

        when(this.playerRepositoryMock.findById(1L)).thenReturn(Optional.of(p1));
        Player playerDb = this.playerService.findById(1l);
        Assert.assertEquals(p1, playerDb);
    }

    @Test(expected = NotFoundException.class)
    public void testFindById_NotFound() throws NotFoundException {
        when(this.playerRepositoryMock.findById(1L)).thenReturn(Optional.empty());
        this.playerService.findById(1l);
    }

    @Test
    public void testCreate() {
        Player p1 = new Player();
        p1.setName("Nome Player");

        when(this.playerRepositoryMock.save(p1)).thenReturn(p1);
        Player p1Db = this.playerService.create(p1);
        Assert.assertNotNull(p1Db);
    }

    @Test
    public void testFindAll() throws NotFoundException {
        Player p1 = new Player();
        p1.setName("Nome Player 1");
        Player p2 = new Player();
        p2.setName("Nome Player 2");

        when(this.playerRepositoryMock.findAll()).thenReturn(Arrays.asList(p1, p2));
        List<Player> players = this.playerService.findAll();
        Assert.assertNotNull(players);
    }

    @Test(expected = NotFoundException.class)
    public void testFindAll_Empty() throws NotFoundException {
        when(this.playerRepositoryMock.findAll()).thenReturn(new ArrayList<>());
        this.playerService.findAll();
    }


}

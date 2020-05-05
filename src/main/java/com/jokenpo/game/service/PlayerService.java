package com.jokenpo.game.service;

import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Player;
import com.jokenpo.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
	
    @Autowired
    private PlayerRepository playerRepository;

    public Player create(Player player) {
        return this.playerRepository.save(player);
    }

    public List<Player> findAll() throws NotFoundException {
        List<Player> players =  this.playerRepository.findAll();
        if (players.isEmpty()) {
            throw new NotFoundException();
        }

        return players;
    }

    public Player findById(Long id) throws NotFoundException {
        Optional<Player> player = this.playerRepository.findById(id);
        return player.orElseThrow(() -> new NotFoundException());
    }

    public void delete(Long id) {
        this.playerRepository.deleteById(id);
    }

}

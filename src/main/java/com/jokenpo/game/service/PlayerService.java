package com.jokenpo.game.service;

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

    public List<Player> findAll() {
        return this.playerRepository.findAll();
    }

    public Optional<Player> findById(Long id) {
        return this.playerRepository.findById(id);
    }

    public void delete(Long id) {
        this.playerRepository.deleteById(id);
    }

}

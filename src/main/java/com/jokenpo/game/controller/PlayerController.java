package com.jokenpo.game.controller;

import com.jokenpo.game.model.Player;
import com.jokenpo.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public Player create(@RequestBody Player player) {
        return this.playerService.create(player);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Player> players = this.playerService.findAll();

        if (!players.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(players);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Player> player = this.playerService.findById(id);

        if (player.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(player.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.playerService.delete(id);
    }
}

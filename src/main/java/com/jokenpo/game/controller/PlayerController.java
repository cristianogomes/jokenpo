package com.jokenpo.game.controller;

import com.jokenpo.game.dto.PlayerDTO;
import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Player;
import com.jokenpo.game.model.Tool;
import com.jokenpo.game.response.Response;
import com.jokenpo.game.response.ResponseBuilder;
import com.jokenpo.game.service.PlayerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody PlayerDTO playerDTO) {
        Player player = new Player();
        BeanUtils.copyProperties(playerDTO, player);

        Player playerDb = this.playerService.create(player);

        return new ResponseBuilder<Player>().withData(playerDb).build();
    }

    @GetMapping
    public ResponseEntity<Response<Player>> getAll() throws NotFoundException {
        List<Player> players = this.playerService.findAll();

        return new ResponseBuilder<Player>().withData(players).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Player>> getById(@PathVariable Long id) throws NotFoundException {
        Player player = this.playerService.findById(id);

        return new ResponseBuilder<Player>().withData(player).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.playerService.delete(id);

        return new ResponseBuilder<Tool>().withMessage("Data deleted").build();
    }
}

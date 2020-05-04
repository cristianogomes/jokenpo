package com.jokenpo.game.controller;

import java.util.List;

import com.jokenpo.game.dto.MoveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jokenpo.game.model.Result;
import com.jokenpo.game.service.GameService;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;
    
    @PostMapping("/")
    public ResponseEntity postMove(@RequestBody MoveDTO move) {
    	this.gameService.doMove(move);
    	return ResponseEntity.ok().build();
    }

    @GetMapping("/result")
    public ResponseEntity getResult() {
        List<Result> results = this.gameService.getResult();
        return ResponseEntity.ok().body(results);
    }
}

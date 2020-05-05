package com.jokenpo.game.controller;

import java.util.List;

import com.jokenpo.game.dto.MoveDTO;
import com.jokenpo.game.exception.MoveAlreadyExistsException;
import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Action;
import com.jokenpo.game.model.Move;
import com.jokenpo.game.response.Response;
import com.jokenpo.game.response.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jokenpo.game.model.Result;
import com.jokenpo.game.service.GameService;

import javax.validation.Valid;

@RestController
@RequestMapping("/jokenpo")
public class GameController {

    @Autowired
    private GameService gameService;
    
    @PostMapping("/play")
    public ResponseEntity<Response<Action>> postMove(@Valid @RequestBody MoveDTO move) throws NotFoundException, MoveAlreadyExistsException {
    	Move playerMove = this.gameService.doMove(move);

        return new ResponseBuilder<Move>().withData(playerMove).build();
    }

    @GetMapping("/result")
    public ResponseEntity<Response<Result>> getResult() {
        List<Result> results = this.gameService.getResult();
        this.gameService.clear();
        return new ResponseBuilder<Result>().withData(results).build();
    }

    @GetMapping("/reset")
    public ResponseEntity<Response<Result>> clearGame() {
        this.gameService.clear();

        return new ResponseBuilder<Result>().withMessage("Clear").build();
    }
}

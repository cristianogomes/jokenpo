package com.jokenpo.game.controller;

import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Action;
import com.jokenpo.game.model.Tool;
import com.jokenpo.game.response.Response;
import com.jokenpo.game.response.ResponseBuilder;
import com.jokenpo.game.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping
    public ResponseEntity<Response<Action>> create(@RequestBody Action action) {
        Action actionDb = this.actionService.create(action);

        return new ResponseBuilder<Action>().withData(actionDb).build();
    }

    @GetMapping
    public ResponseEntity<Response<Action>> getAll() throws NotFoundException {
        List<Action> actions = this.actionService.findAll();

        return new ResponseBuilder<Action>().withData(actions).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Action>> getById(@PathVariable Long id) throws NotFoundException {
        Action action = this.actionService.findById(id);

        return new ResponseBuilder<Action>().withData(action).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.actionService.delete(id);

        return new ResponseBuilder<Tool>().withMessage("Data deleted").build();
    }
}

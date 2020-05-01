package com.jokenpo.game.controller;

import com.jokenpo.game.model.Action;
import com.jokenpo.game.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @PostMapping
    public Action create(@RequestBody Action action) {
        return this.actionService.create(action);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Action> actions = this.actionService.findAll();

        if (!actions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(actions);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Action> action = this.actionService.findById(id);

        if (action.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(action.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.actionService.delete(id);
    }
}

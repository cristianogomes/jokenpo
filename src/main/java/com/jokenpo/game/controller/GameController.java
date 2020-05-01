package com.jokenpo.game.controller;

import com.jokenpo.game.model.Action;
import com.jokenpo.game.model.Tool;
import com.jokenpo.game.repository.ActionRepository;
import com.jokenpo.game.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private ActionRepository actionRepository;

    @GetMapping("/tools")
    public List<Tool> getTools() {
        return toolRepository.findAll();
    }

    @GetMapping("/actions")
    public List<Action> getAction() {
        return actionRepository.findAll();
    }

}

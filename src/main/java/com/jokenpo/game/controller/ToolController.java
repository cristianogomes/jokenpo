package com.jokenpo.game.controller;

import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Tool;
import com.jokenpo.game.response.Response;
import com.jokenpo.game.response.ResponseBuilder;
import com.jokenpo.game.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @PostMapping
    public ResponseEntity create(@RequestBody Tool tool) {
        Tool toolDb = toolService.create(tool);

        return new ResponseBuilder<Tool>().withData(toolDb).build();
    }

    @GetMapping
    public ResponseEntity<Response<Tool>> getAll() throws NotFoundException {
        List<Tool> tools = toolService.findAll();

        return new ResponseBuilder<Tool>().withData(tools).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Tool>> getById(@PathVariable Long id) throws NotFoundException {
        Tool tool = toolService.findById(id);

        return new ResponseBuilder<Tool>().withData(tool).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        this.toolService.delete(id);

        return new ResponseBuilder<Tool>().withMessage("Data deleted").build();
    }
}

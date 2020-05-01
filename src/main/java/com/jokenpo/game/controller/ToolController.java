package com.jokenpo.game.controller;

import com.jokenpo.game.model.Tool;
import com.jokenpo.game.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private ToolService toolService;

    @PostMapping
    public Tool create(@RequestBody Tool tool) {
        return toolService.create(tool);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Tool> tools = toolService.findAll();

        if (!tools.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(tools);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Tool> tool = toolService.findById(id);

        if (tool.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(tool.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.toolService.delete(id);
    }
}

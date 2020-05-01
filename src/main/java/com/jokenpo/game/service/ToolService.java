package com.jokenpo.game.service;

import com.jokenpo.game.model.Tool;
import com.jokenpo.game.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolService {

    @Autowired
    private ToolRepository toolRepository;

    public Tool create(Tool tool) {
        return this.toolRepository.save(tool);
    }

    public Optional<Tool> findById(Long id) {
        return this.toolRepository.findById(id);
    }

    public List<Tool> findAll() {
        return this.toolRepository.findAll();
    }

    public void delete(Long id) {
        this.toolRepository.deleteById(id);
    }
}
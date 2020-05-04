package com.jokenpo.game.service;

import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Action;
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

    public Tool findById(Long id) throws NotFoundException {
        Optional<Tool> tool = this.toolRepository.findById(id);
        return tool.orElseThrow(() -> new NotFoundException());
    }

    public List<Tool> findAll() throws NotFoundException {
        List<Tool> tools =  this.toolRepository.findAll();
        if (tools.isEmpty()) {
            throw new NotFoundException();
        }

        return tools;
    }

    public void delete(Long id) {
        this.toolRepository.deleteById(id);
    }
}

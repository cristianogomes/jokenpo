package com.jokenpo.game.service;

import com.jokenpo.game.model.Action;
import com.jokenpo.game.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    public Action create(Action action) {
        return this.actionRepository.save(action);
    }

    public List<Action> findAll() {
        return this.actionRepository.findAll();
    }

    public Optional<Action> findById(Long id) {
        return this.actionRepository.findById(id);
    }

    public void delete(Long id) {
        this.actionRepository.deleteById(id);
    }
}

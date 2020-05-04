package com.jokenpo.game.service;

import com.jokenpo.game.exception.NotFoundException;
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

    public List<Action> findAll() throws NotFoundException {
        List<Action> actions =  this.actionRepository.findAll();
        if (actions.isEmpty()) {
            throw new NotFoundException();
        }

        return actions;
    }

    public Action findById(Long id) throws NotFoundException {
        Optional<Action> entity = this.actionRepository.findById(id);
        return entity.orElseThrow(() -> new NotFoundException());
    }

    public void delete(Long id) {
        this.actionRepository.deleteById(id);
    }
}

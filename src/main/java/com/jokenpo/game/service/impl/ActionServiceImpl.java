package com.jokenpo.game.service.impl;

import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Action;
import com.jokenpo.game.model.Tool;
import com.jokenpo.game.repository.ActionRepository;
import com.jokenpo.game.repository.ToolRepository;
import com.jokenpo.game.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActionServiceImpl extends AbstractServiceImpl<Long, Action, ActionRepository> implements ActionService {

    @Autowired
    private ToolRepository toolRepository;

    public Action createAction(Action action) throws NotFoundException {
        Optional<Tool> source = this.toolRepository.findById(action.getSource().getId());
        Optional<Tool> target = this.toolRepository.findById(action.getTarget().getId());

        if (!source.isPresent() || !target.isPresent()) {
            throw new NotFoundException();
        }

        action.setSource(source.get());
        action.setTarget(target.get());

        return this.create(action);
    }

}

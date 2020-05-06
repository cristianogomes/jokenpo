package com.jokenpo.game.service.impl;

import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.Tool;
import com.jokenpo.game.repository.ActionRepository;
import com.jokenpo.game.repository.ToolRepository;
import com.jokenpo.game.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ToolServiceImpl extends AbstractServiceImpl<Long, Tool, ToolRepository> implements ToolService {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private ActionRepository actionRepository;

    @Transactional
    public void delete(Long id) throws NotFoundException {
        Tool tool = this.findById(id);

        this.actionRepository.deleteActionByTool(tool);
        tool.setActions(null);
        this.toolRepository.delete(tool);
    }

}

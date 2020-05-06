package com.jokenpo.game.service.impl;

import com.jokenpo.game.model.Action;
import com.jokenpo.game.repository.ActionRepository;
import com.jokenpo.game.service.ActionService;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl extends AbstractServiceImpl<Long, Action, ActionRepository> implements ActionService {

}

package com.jokenpo.game.service.impl;

import com.jokenpo.game.model.Tool;
import com.jokenpo.game.repository.ToolRepository;
import com.jokenpo.game.service.ToolService;
import org.springframework.stereotype.Service;

@Service
public class ToolServiceImpl extends AbstractServiceImpl<Long, Tool, ToolRepository> implements ToolService {

}

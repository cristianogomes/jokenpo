package com.jokenpo.game.service.impl;

import com.jokenpo.game.model.Player;
import com.jokenpo.game.repository.PlayerRepository;
import com.jokenpo.game.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl extends AbstractServiceImpl<Long, Player, PlayerRepository> implements PlayerService {

}

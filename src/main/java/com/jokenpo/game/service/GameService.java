package com.jokenpo.game.service;

import java.util.List;

import com.jokenpo.game.dto.MoveDTO;
import com.jokenpo.game.exception.MoveAlreadyExistsException;
import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.helper.GameHelper;
import com.jokenpo.game.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private PlayerService playerService;
    private ToolService toolService;

    private GameHelper gameHelper = GameHelper.getInstance();

    @Autowired
    public GameService(PlayerService playerService, ToolService toolService) {
        this.playerService = playerService;
        this.toolService = toolService;
    }

    public void clear() {
        this.gameHelper.clear();
    }
	
	public Move doMove(MoveDTO move) throws NotFoundException, MoveAlreadyExistsException {
        Player player = this.playerService.findById(move.getPlayerId());
        Tool tool = this.toolService.findById(move.getToolId());

        return this.gameHelper.makeAMove(player, tool);
	}

	public List<Result> getResult() {
        return this.gameHelper.getResult();
    }
}

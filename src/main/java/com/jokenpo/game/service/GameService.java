package com.jokenpo.game.service;

import java.util.ArrayList;
import java.util.List;

import com.jokenpo.game.dto.MoveDTO;
import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.helper.GameHelper;
import com.jokenpo.game.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jokenpo.game.enums.GameStatus;

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
	
	public Move doMove(MoveDTO move) throws NotFoundException {
        Player player = this.playerService.findById(move.getPlayerId());
        Tool tool = this.toolService.findById(move.getToolId());

        Move playerMove = new Move();
        playerMove.setPlayer(player);
        playerMove.setTool(tool);

        this.gameHelper.addMove(playerMove);

        return playerMove;
	}

	public List<Result> getResult() {
        return getWinner(this.gameHelper.getMoves());
    }

	public List<Result> getWinner(List<Move> moves) {
        List<Result> results = new ArrayList<>();

        for (Move movePlayer1 : moves) {
            Result result = new Result();
            result.setMove(movePlayer1);
            result.setPlayer(movePlayer1.getPlayer());
            results.add(result);

            for (Move movePlayer2 : moves) {
                if (!movePlayer1.getPlayer().equals(movePlayer2.getPlayer())) {
                    GameStatus value = getWinner(movePlayer1.getTool(), movePlayer2.getTool());

                    if (GameStatus.WIN.equals(value)) {
                        result.addScore();
                    }
                }
            }
        }

        return results;
    }

    private GameStatus getWinner(Tool tool1, Tool tool2) {
        if (tool1.equals(tool2)) {
            return GameStatus.TIE;
        }

        for (Action action : tool1.getActions()) {
            if (action.getTarget().equals(tool2)) {
                return GameStatus.WIN;
            }
        }

        return GameStatus.LOSE;
    }

}

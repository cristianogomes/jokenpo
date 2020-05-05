package com.jokenpo.game.helper;

import com.jokenpo.game.enums.GameStatus;
import com.jokenpo.game.exception.MoveAlreadyExistsException;
import com.jokenpo.game.model.*;

import java.util.*;

public class GameHelper {
    private static final GameHelper GAME_HELPER = new GameHelper();

    private Map<Player, Move> moves = new HashMap<>();

    private GameHelper() {}

    public static GameHelper getInstance() {
        return GameHelper.GAME_HELPER;
    }

    public void clear() {
        moves = new HashMap<>();
    }

    public Move makeAMove(Player player, Tool tool) throws MoveAlreadyExistsException {
        Move playerMove = new Move(player, tool);
        this.addMove(playerMove);

        return playerMove;
    }

    private void addMove(Move move) throws MoveAlreadyExistsException {
        if (this.moves.containsKey(move.getPlayer())) {
            throw new MoveAlreadyExistsException();
        }

        this.moves.put(move.getPlayer(), move);
    }

    public List<Result> getResult() {
        return getWinner(this.getMoves());
    }

    private List<Result> getWinner(List<Move> moves) {
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

    private List<Move> getMoves() {
        List<Move> list = new ArrayList<>();
        list.addAll(this.moves.values());

        return list;
    }
}

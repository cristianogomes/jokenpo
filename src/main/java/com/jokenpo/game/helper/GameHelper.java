package com.jokenpo.game.helper;

import com.jokenpo.game.model.Move;
import com.jokenpo.game.model.Player;

import java.util.*;

public class GameHelper {
    private static final GameHelper GAME_HELPER = new GameHelper();

    private Map<Player, Move> moves = new HashMap<>();

    private GameHelper() {}

    public static GameHelper getInstance() {
        return GAME_HELPER;
    }

    public void addMove(Move move) {
        this.moves.put(move.getPlayer(), move);
    }

    public List<Move> getMoves() {
        List<Move> list = new ArrayList<>();
        list.addAll(this.moves.values());

        return list;
    }
}

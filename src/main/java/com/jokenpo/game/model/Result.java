package com.jokenpo.game.model;

import java.util.Objects;

public class Result {

	private Move move;
	
    private Player player;

    private int score;

    public void addScore() {
        score++;
    }

    public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}
	
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(player, result.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}

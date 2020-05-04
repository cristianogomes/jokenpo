package com.jokenpo.game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Move {

    private Player player;

    private Tool tool;

    @JsonIgnore
    private Result result;

    public Move() {
        this.result = new Result();
    }

    public void addScore() {
        this.result.addScore();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

}

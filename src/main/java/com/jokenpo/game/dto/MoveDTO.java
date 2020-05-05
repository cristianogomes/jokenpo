package com.jokenpo.game.dto;

import javax.validation.constraints.NotNull;

public class MoveDTO {

    @NotNull
    private Long playerId;

    @NotNull
    private Long toolId;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }
}

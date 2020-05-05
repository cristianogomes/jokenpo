package com.jokenpo.game.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

public class ActionDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Long id;

    @NotNull
    public String description;

    @NotNull
    private ToolDTO source;

    @NotNull
    private ToolDTO target;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ToolDTO getSource() {
        return source;
    }

    public void setSource(ToolDTO source) {
        this.source = source;
    }

    public ToolDTO getTarget() {
        return target;
    }

    public void setTarget(ToolDTO target) {
        this.target = target;
    }
}

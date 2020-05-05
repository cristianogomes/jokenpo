package com.jokenpo.game.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlayerDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @NotNull
    @Size(max = 20)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

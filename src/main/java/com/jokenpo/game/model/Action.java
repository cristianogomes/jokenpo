package com.jokenpo.game.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Action {

    @Id
    private Long id;

    private String description;

    @JsonIgnore
    @ManyToOne
    private Tool source;

    @JsonIgnoreProperties("actions")
    @ManyToOne
    private Tool target;

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

    public Tool getSource() {
        return source;
    }

    public void setSource(Tool source) {
        this.source = source;
    }

    public Tool getTarget() {
        return target;
    }

    public void setTarget(Tool target) {
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return id.equals(action.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

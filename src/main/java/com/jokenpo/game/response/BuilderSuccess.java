package com.jokenpo.game.response;

import java.util.List;

public class BuilderSuccess<R> extends Builder {

    public BuilderSuccess(List<R> data) {
        response = new Response<>(data);
    }

    public BuilderSuccess(String message) {
        if (response == null) {
            response = new Response<>(message);
            return;
        }

        response.setMessage(message);
    }

    public BuilderSuccess withMessage(String message) {
        if (response == null) {
            throw new IllegalArgumentException("response == null");
        }

        response.setMessage(message);
        return this;
    }
}
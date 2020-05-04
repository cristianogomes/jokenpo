package com.jokenpo.game.response;

public class BuilderError<R> extends Builder {

    public BuilderError(_Error error) {
        response = new Response<R>(error);
    }

}

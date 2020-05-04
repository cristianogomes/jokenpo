package com.jokenpo.game.response;

import java.util.Arrays;
import java.util.List;

public class ResponseBuilder<R> {

    private BuilderError builderError;

    private BuilderSuccess builderSuccess;

    public ResponseBuilder() { }

    public BuilderSuccess withData(R data) {
        return withData(Arrays.asList(data));
    }

    public BuilderSuccess withData(List<R> data) {
        if (builderSuccess == null) {
            builderSuccess = new BuilderSuccess(data);
        }

        return builderSuccess;
    }

    public BuilderSuccess withMessage(String message) {
        if (builderSuccess == null) {
            builderSuccess = new BuilderSuccess(message);
        }

        return builderSuccess;
    }

    public BuilderError withError(_Error error) {
        if (builderError == null) {
            builderError = new BuilderError(error);
        }

        return builderError;
    }
}
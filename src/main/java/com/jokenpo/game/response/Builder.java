package com.jokenpo.game.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class Builder<R> {

    protected Response<R> response;

    public Builder status(HttpStatus httpStatus) {
        response.setStatus(httpStatus);
        return this;
    }

    public ResponseEntity<Response<R>> build() {
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

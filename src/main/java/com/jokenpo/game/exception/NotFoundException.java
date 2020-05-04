package com.jokenpo.game.exception;

public class NotFoundException extends BusinessException {

    private static final String MESSAGE = "Register not found!";

    public NotFoundException() {
        super(String.valueOf(MESSAGE.hashCode()), MESSAGE);
    }

    public NotFoundException(String code, String message) {
        super(code, message);
    }

}

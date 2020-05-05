package com.jokenpo.game.exception;

public class MoveAlreadyExistsException extends BusinessException {

    private static final String MESSAGE = "Move Already Exists";

    public MoveAlreadyExistsException() {
        super(String.valueOf(MESSAGE.hashCode()), MESSAGE);
    }

    public MoveAlreadyExistsException(String code, String message) {
        super(code, message);
    }

}

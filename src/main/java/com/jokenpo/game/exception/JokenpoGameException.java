package com.jokenpo.game.exception;

public class JokenpoGameException extends BusinessException {

    private static final String MESSAGE = "Jokenpo Game Exception";

    public JokenpoGameException() {
        super(String.valueOf(MESSAGE.hashCode()), MESSAGE);
    }

    public JokenpoGameException(String code, String message) {
        super(code, message);
    }

}

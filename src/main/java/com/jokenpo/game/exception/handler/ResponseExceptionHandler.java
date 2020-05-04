package com.jokenpo.game.exception.handler;

import com.jokenpo.game.exception.BusinessException;
import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.response.Response;
import com.jokenpo.game.response.ResponseBuilder;
import com.jokenpo.game.response._Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Response> notFoundException(NotFoundException e) {
        return new ResponseBuilder().withError(getError(e)).status(HttpStatus.NOT_FOUND).build();
    }

    private _Error getError(BusinessException e) {
        return getError(e.getCode(), e.getMessage());
    }

    private _Error getError(String code, String message) {
        return new _Error(code, message);
    }
}

package com.jokenpo.game.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

public class Response<R> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private _Error error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<R> data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phrase;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Long timestamp;

    public Response() {
        this.timestamp = new Date().getTime();
        status = HttpStatus.OK.value();
        phrase = HttpStatus.OK.getReasonPhrase();
    }

    public Response(String message) {
        this();
        this.setMessage(message);
    }

    public Response(_Error error) {
        this();
        this.setError(error);
    }

    public Response(List<R> data) {
        this();
        this.setData(data);
    }

    private void setData(List<R> data) {
        if (data == null) {
            throw new IllegalArgumentException("data == null");
        }

        this.data = data;
    }

    private void setError(_Error error) {
        if (error == null) {
            throw new IllegalArgumentException("error == null");
        }

        this.error = error;
    }

    public void setStatus(HttpStatus httpStatus) {
        if (httpStatus == null) {
            throw new IllegalArgumentException("http == null");
        }

        this.status = httpStatus.value();
        this.phrase = httpStatus.getReasonPhrase();
    }

    public _Error getError() {
        return error;
    }

    public List<R> getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

}

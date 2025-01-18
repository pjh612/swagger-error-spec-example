package com.example.commonapi.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class StatusException extends Exception {
    @JsonIgnore
    private final Integer status;
    public StatusException(String code, String message, int status) {
        super(code, message);
        this.status = status;
    }
}

package com.example.commonapi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.Collections;

public class ExceptionResponse {
    private final Collection<? extends Exception> errors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Object meta;

    public ExceptionResponse(String code, String message) {
        this(Collections.singletonList(new Exception(code, message)), null);
    }

    public ExceptionResponse(String code, String message, Object meta) {
        this(Collections.singletonList(new Exception(code, message)), meta);
    }

    public ExceptionResponse(java.lang.Error error) {
        this(error, null);
    }

    public ExceptionResponse(java.lang.Error error, Object meta) {
        this((Collection) Collections.singletonList(error), meta);
    }

    public ExceptionResponse(Collection<? extends Exception> errors) {
        this(errors, null);
    }

    public ExceptionResponse(Collection<? extends Exception> errors, Object meta) {
        this.errors = errors;
        this.meta = meta;
    }

    public Collection<? extends Exception> getErrors() {
        return this.errors;
    }

    public Object getMeta() {
        return this.meta;
    }
}

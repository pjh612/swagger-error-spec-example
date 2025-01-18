package com.example.commonapi.exception;

public class Exception {
    private final String code;
    private final String message;

    public Exception(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Exception(String code) {
        this(code, (String)null);
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}

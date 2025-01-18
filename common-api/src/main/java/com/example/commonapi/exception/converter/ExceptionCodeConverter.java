package com.example.commonapi.exception.converter;

import com.example.commonapi.exception.Exception;
import com.example.commonapi.exception.ExceptionResponse;
import com.example.commonapi.exception.StatusException;

public interface ExceptionCodeConverter {

    StatusException[] toError(String[] codes);

    ExceptionResponse toExceptionResponse(Exception[] errors);
}

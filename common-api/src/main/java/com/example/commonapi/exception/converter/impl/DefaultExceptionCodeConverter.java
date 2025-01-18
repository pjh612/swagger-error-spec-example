package com.example.commonapi.exception.converter.impl;

import com.example.commonapi.exception.Exception;
import com.example.commonapi.exception.*;
import com.example.commonapi.exception.converter.ExceptionCodeConverter;

import java.util.Arrays;
import java.util.List;

public class DefaultExceptionCodeConverter<E extends Enum<E> & ExceptionContent> implements ExceptionCodeConverter {
    private final Class<? extends ExceptionContent> exceptionContent;

    public DefaultExceptionCodeConverter(Class<E> exceptionContent) {
        this.exceptionContent = exceptionContent;
    }

    @Override
    public StatusException[] toError(String[] codes) {
        return Arrays.stream(codes)
                .map(rawCode -> Arrays.stream(exceptionContent.getEnumConstants())
                        .filter(it -> it.getCode().equals(rawCode))
                        .findFirst()
                        .orElseThrow(() -> new InvalidCodeException("code[" + rawCode + "] is not found")))
                .map(code -> new StatusException(code.getCode(), code.getDetail(), code.getStatus()))
                .toArray(StatusException[]::new);
    }

    @Override
    public ExceptionResponse toExceptionResponse(Exception[] errors) {
        return new ExceptionResponse(List.of(errors));
    }
}

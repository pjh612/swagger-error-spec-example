package com.example.exampleapi.exception;

import com.example.commonapi.exception.*;
import com.example.commonapi.exception.Exception;
import com.example.commonapi.exception.converter.ExceptionCodeConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BasicExceptionCodeConverter implements ExceptionCodeConverter {
    @Override
    public StatusException[] toError(String[] codes) {
        return Arrays.stream(codes)
                .map(rawCode -> ExceptionCode.lookupByCode(rawCode).orElseThrow(() -> new InvalidCodeException("code[" + rawCode + "] is not found")))
                .map(code -> new StatusException(code.getCode(), code.getDetail(), code.getStatus()))
                .toArray(StatusException[]::new);
    }

    @Override
    public ExceptionResponse toExceptionResponse(Exception[] errors) {
        return new ExceptionResponse(List.of(errors));
    }
}

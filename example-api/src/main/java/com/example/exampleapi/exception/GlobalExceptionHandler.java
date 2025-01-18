package com.example.exampleapi.exception;

import com.example.commonapi.exception.BusinessException;
import com.example.commonapi.exception.ExceptionContent;
import com.example.commonapi.exception.ExceptionResponse;
import com.example.commonapi.exception.StatusException;
import com.example.commonapi.exception.converter.ExceptionCodeConverter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ExceptionCodeConverter exceptionCodeConverter;

    public GlobalExceptionHandler(ExceptionCodeConverter exceptionCodeConverter) {
        this.exceptionCodeConverter = exceptionCodeConverter;
    }

    @ExceptionHandler(BusinessException.class)
    public ExceptionResponse handleBusinessException(BusinessException e) {
        StatusException[] errors = exceptionCodeConverter.toError(e.getExceptionContent()
                .stream()
                .map(ExceptionContent::getCode)
                .toArray(String[]::new));

        return exceptionCodeConverter.toExceptionResponse(errors);
    }
}

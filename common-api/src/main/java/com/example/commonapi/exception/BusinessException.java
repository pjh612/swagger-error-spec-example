package com.example.commonapi.exception;

import lombok.Getter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BusinessException extends RuntimeException{
    private final String title;
    private final List<ExceptionContent> exceptionContent;

    public BusinessException(ExceptionContent exceptionContent) {
        this(null, exceptionContent);
    }

    public BusinessException(String message, ExceptionContent exceptionContent) {
        this(message, exceptionContent, null);
    }

    public BusinessException(String message, ExceptionContent exceptionContent, Throwable cause) {
        this(exceptionContent.getTitle(), message, Collections.singletonList(exceptionContent), cause);
    }

    public BusinessException(String title, String message, List<ExceptionContent> exceptionContents) {
        this(title, message, exceptionContents, null);
    }

    public BusinessException(String title, String message, Collection<? extends ExceptionContent> exceptionContents, Throwable cause) {
        this(title, message, exceptionContents.stream().collect(Collectors.toUnmodifiableList()), cause);
    }

    public BusinessException(String title, String message, List<ExceptionContent> exceptionContent, Throwable cause) {
        super(message, cause);
        this.title = title;
        this.exceptionContent = exceptionContent;
    }
}

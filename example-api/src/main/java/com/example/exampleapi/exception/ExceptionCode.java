package com.example.exampleapi.exception;

import com.example.commonapi.exception.ExceptionContent;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum ExceptionCode implements ExceptionContent {
    NOT_FOUND_ENTITY("C-001", "대상을 찾을 수 없습니다.", 404),
    INVALID_STATUS("C-002", "유효하지않은 상태입니다.", 400);

    private final String code;
    private final String detail;
    private final int status;

    private static final Map<String, ExceptionCode> codeMap = Arrays.stream(ExceptionCode.values()).collect(Collectors.toMap(ExceptionCode::getCode, exceptionCode -> exceptionCode));

    ExceptionCode(String code, String detail, Integer status) {
        this.code = code;
        this.detail = detail;
        this.status = status;

    }

    @Override
    public String getTitle() {
        return this.name();
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDetail() {
        return detail;
    }

    public Integer getStatus() {
        return status;
    }

    public String toString() {
        return name() + "-" + code + ":" + detail;
    }

    public static Optional<ExceptionCode> lookupByCode(String code) {
        return Optional.ofNullable(codeMap.get(code));
    }
}

package com.example.commonapi.exception;

import java.util.EnumMap;
import java.util.Optional;

public interface ExceptionContent {

    String getTitle();

    String getCode();

    String getDetail();

    Integer getStatus();
}

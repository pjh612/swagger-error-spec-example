package com.example.exampleapi.exception;

import com.example.commonapi.exception.converter.impl.DefaultExceptionCodeConverter;
import com.example.commonapi.exception.converter.ExceptionCodeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionConfig {

    @Bean
    ExceptionCodeConverter exceptionCodeConverter() {
        return new DefaultExceptionCodeConverter<>(ExceptionCode.class);
    }
}

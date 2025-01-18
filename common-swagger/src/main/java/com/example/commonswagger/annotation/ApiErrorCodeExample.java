package com.example.commonswagger.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface ApiErrorCodeExample {
    ExceptionCodeExample[] examples();
}

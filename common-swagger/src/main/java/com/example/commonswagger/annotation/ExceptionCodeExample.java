package com.example.commonswagger.annotation;

import com.example.commonapi.exception.strategy.impl.DefaultStatusDetermineStrategy;
import com.example.commonapi.exception.strategy.StatusDetermineStrategy;

public @interface ExceptionCodeExample {

    String title();

    String description() default "";

    String[] codes();

    Class<? extends StatusDetermineStrategy> statusDetermineStrategy() default DefaultStatusDetermineStrategy.class;

}


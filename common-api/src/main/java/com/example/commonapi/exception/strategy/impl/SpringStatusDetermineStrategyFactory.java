package com.example.commonapi.exception.strategy.impl;

import com.example.commonapi.exception.strategy.StatusDetermineStrategy;
import com.example.commonapi.exception.strategy.StatusDetermineStrategyFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

@Slf4j
public class SpringStatusDetermineStrategyFactory implements StatusDetermineStrategyFactory {
    private final ApplicationContext applicationContext;

    public SpringStatusDetermineStrategyFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public StatusDetermineStrategy getInstance(Class<? extends StatusDetermineStrategy> def) {
        try {
            return applicationContext.getBean(def);
        } catch (NoSuchBeanDefinitionException e) {
            log.warn("StatusDetermineStrategy[{}] is not declared, your exception specification may differ from the actual response.", def.getTypeName());

            return ClassUtil.createInstance(def, false);
        }
    }
}

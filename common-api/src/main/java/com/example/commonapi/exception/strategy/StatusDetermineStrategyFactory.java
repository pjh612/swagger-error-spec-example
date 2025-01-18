package com.example.commonapi.exception.strategy;

public interface StatusDetermineStrategyFactory {
    StatusDetermineStrategy getInstance(Class<? extends StatusDetermineStrategy> def);
}

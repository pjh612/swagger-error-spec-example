package com.example.commonapi.exception.strategy.impl;

import com.example.commonapi.exception.strategy.StatusDetermineStrategy;

import java.util.Arrays;
import java.util.Comparator;

public class DefaultStatusDetermineStrategy implements StatusDetermineStrategy {
    @Override
    public Integer determine(Integer[] status) {
        return Arrays.stream(status).max(Comparator.naturalOrder()).orElse(500);
    }
}

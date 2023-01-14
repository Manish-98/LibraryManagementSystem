package com.personal.librarymanagementsystem.utils;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

@TestConfiguration
public class TestBeans {
    @Bean
    @Primary
    public Clock mockClock() {
        return Clock.fixed(Instant.parse("2023-01-01T00:00:00.00Z"), ZoneOffset.UTC);
    }
}

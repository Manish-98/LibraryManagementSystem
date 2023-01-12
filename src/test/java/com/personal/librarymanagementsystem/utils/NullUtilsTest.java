package com.personal.librarymanagementsystem.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NullUtilsTest {

    @Test
    void shouldReturnValueIfNotNull() {
        String actual = NullUtils.getOrDefault("actualValue", "defaultValue");
        Assertions.assertEquals("actualValue", actual);
    }

    @Test
    void shouldReturnDefaultValueIfNull() {
        String actual = NullUtils.getOrDefault(null, "default");
        Assertions.assertEquals("default", actual);
    }
}

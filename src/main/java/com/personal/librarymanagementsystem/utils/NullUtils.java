package com.personal.librarymanagementsystem.utils;

public class NullUtils {

    public static <T> T getOrDefault(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }
}

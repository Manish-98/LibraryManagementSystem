package com.personal.librarymanagementsystem.utils;

import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class TestUtils {
    @NotNull
    public static HttpEntity<String> getHttpEntity(@Language("JSON") String requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new HttpEntity<>(requestBody, headers);
    }
}

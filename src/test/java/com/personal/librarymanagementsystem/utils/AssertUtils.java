package com.personal.librarymanagementsystem.utils;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AssertUtils {
    public static void assertResponse(String expectedResponse, ResponseEntity<String> response, HttpStatus httpStatus) throws JSONException {
        Assertions.assertEquals(httpStatus, response.getStatusCode());
        JSONAssert.assertEquals(expectedResponse, response.getBody(), JSONCompareMode.LENIENT);
    }
}

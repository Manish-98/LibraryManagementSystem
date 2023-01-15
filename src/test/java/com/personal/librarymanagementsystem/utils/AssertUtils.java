package com.personal.librarymanagementsystem.utils;

import org.assertj.core.api.SoftAssertions;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AssertUtils {
    public static void assertResponseLenient(String expectedResponse, ResponseEntity<String> response, HttpStatus httpStatus) throws JSONException {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getStatusCode()).isEqualTo(httpStatus);
        JSONCompareResult result = JSONCompare.compareJSON(expectedResponse, response.getBody(), JSONCompareMode.LENIENT);
        if (result.failed()) {
            softAssertions.assertThat(result.getMessage()).isEqualTo("");
        }
        softAssertions.assertAll();
    }

    public static void assertResponseNonExtensible(String expectedResponse, ResponseEntity<String> response, HttpStatus httpStatus) throws JSONException {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getStatusCode()).isEqualTo(httpStatus);
        JSONCompareResult result = JSONCompare.compareJSON(expectedResponse, response.getBody(), JSONCompareMode.NON_EXTENSIBLE);
        if (result.failed()) {
            softAssertions.assertThat(result.getMessage()).isEqualTo("");
        }
        softAssertions.assertAll();
    }
}

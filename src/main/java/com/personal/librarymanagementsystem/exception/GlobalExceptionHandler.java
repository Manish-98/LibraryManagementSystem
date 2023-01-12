package com.personal.librarymanagementsystem.exception;

import com.personal.librarymanagementsystem.utils.NullUtils;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HashMap<String, List<String>> handleException(MethodArgumentNotValidException ex) {
        HashMap<String, List<String>> errorResponse = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String errorField = error.getField();
            String errorMessage = error.getDefaultMessage();

            List<String> errors = errorResponse.get(errorField);

            if (errors == null) errors = new ArrayList<>() {{
                add(NullUtils.getOrDefault(errorMessage, "Something went wrong"));
            }};
            else errors.add(errorMessage);

            errorResponse.put(errorField, errors);

        }
        log.error(errorResponse.toString());
        return errorResponse;
    }
}

package com.personal.librarymanagementsystem.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NullOrPatternValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private NullOrPattern constraintAnnotation;

    @Test
    void shouldReturnTrueIfValueIsNull() {
        when(constraintAnnotation.regexp()).thenReturn("^\\d{6}$");

        NullOrPatternValidator nullOrPatternValidator = new NullOrPatternValidator();
        nullOrPatternValidator.initialize(constraintAnnotation);

        Assertions.assertTrue(nullOrPatternValidator.isValid(null, context));
    }

    @Test
    void shouldReturnTrueIfPatternIsMatching() {
        when(constraintAnnotation.regexp()).thenReturn("^\\d{6}$");

        NullOrPatternValidator nullOrPatternValidator = new NullOrPatternValidator();
        nullOrPatternValidator.initialize(constraintAnnotation);

        Assertions.assertTrue(nullOrPatternValidator.isValid("123456", context));
    }

    @Test
    void shouldReturnFalseIfPatternIsNotMatching() {
        when(constraintAnnotation.regexp()).thenReturn("^\\d{6}$");

        NullOrPatternValidator nullOrPatternValidator = new NullOrPatternValidator();
        nullOrPatternValidator.initialize(constraintAnnotation);

        Assertions.assertFalse(nullOrPatternValidator.isValid("Something random", context));
    }
}

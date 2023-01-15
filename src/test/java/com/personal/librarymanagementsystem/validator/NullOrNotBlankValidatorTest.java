package com.personal.librarymanagementsystem.validator;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


class NullOrNotBlankValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Test
    void shouldReturnTrueIfValueIsNull() {
        NullOrNotBlankValidator nullOrNotBlankValidator = new NullOrNotBlankValidator();

        Assertions.assertTrue(nullOrNotBlankValidator.isValid(null, context));
    }

    @Test
    void shouldReturnTrueIfValueIsNotBlank() {
        NullOrNotBlankValidator nullOrNotBlankValidator = new NullOrNotBlankValidator();

        Assertions.assertTrue(nullOrNotBlankValidator.isValid("Something random", context));
    }

    @Test
    void shouldReturnFalseIfValueIsBlank() {
        NullOrNotBlankValidator nullOrNotBlankValidator = new NullOrNotBlankValidator();

        Assertions.assertFalse(nullOrNotBlankValidator.isValid("", context));
        Assertions.assertFalse(nullOrNotBlankValidator.isValid("   ", context));
    }
}

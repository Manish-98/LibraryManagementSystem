package com.personal.librarymanagementsystem.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrPatternValidator implements ConstraintValidator<NullOrPattern, String> {

    private String regexp;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.matches(regexp);
    }

    @Override
    public void initialize(NullOrPattern constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }
}

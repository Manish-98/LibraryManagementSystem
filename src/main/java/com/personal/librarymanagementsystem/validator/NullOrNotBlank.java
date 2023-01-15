package com.personal.librarymanagementsystem.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NullOrNotBlankValidator.class})
public @interface NullOrNotBlank {
    String message() default "{javax.validation.constraints.NullOrNotBlank.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};
}

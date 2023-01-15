package com.personal.librarymanagementsystem.validator;

import jakarta.validation.groups.Default;

public class ValidatorGroup {
    public interface CreateRequestValidation extends Default {}

    public interface UpdateRequestValidation extends Default {}
}

package com.personal.librarymanagementsystem.model;

import com.personal.librarymanagementsystem.validator.NullOrNotBlank;
import com.personal.librarymanagementsystem.validator.ValidatorGroup;
import jakarta.validation.constraints.Pattern;

public record Address(
        @NullOrNotBlank(message = "Street should not be blank")
        String street,

        @NullOrNotBlank(message = "City should not be blank")
        String city,

        @NullOrNotBlank(message = "State should not be blank")
        String state,

        @NullOrNotBlank(message = "Country should not be blank")
        String country,

        @Pattern(regexp = "^\\d{6}$", message = "Invalid zip code", groups = {ValidatorGroup.CreateRequestValidation.class})
        String zipCode
) { }

package com.personal.librarymanagementsystem.model;

import jakarta.validation.constraints.Pattern;

public record Address(
        String street,
        String city,
        String state,
        String country,
        @Pattern(regexp = "^\\d{6}$", message = "Invalid zip code")
        String zipCode
) { }

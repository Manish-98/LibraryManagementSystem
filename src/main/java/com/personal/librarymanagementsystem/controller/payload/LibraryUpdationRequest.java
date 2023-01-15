package com.personal.librarymanagementsystem.controller.payload;

import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.validator.NullOrNotBlank;
import jakarta.validation.Valid;

public record LibraryUpdationRequest(
        @NullOrNotBlank(message = "Library name should not be blank")
        String name,

        @Valid
        Address address
) {}

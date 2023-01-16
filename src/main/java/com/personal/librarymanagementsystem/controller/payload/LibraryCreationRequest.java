package com.personal.librarymanagementsystem.controller.payload;

import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record LibraryCreationRequest(
        @NotBlank(message = "Library name should not be blank")
        String name,

        @NotNull(message = "Library address is missing")
        @Valid
        Address address
) {
    public Library toLibrary(UUID id, LocalDateTime localDateTime) {
        return new Library(id, name, address, localDateTime, localDateTime);
    }
}

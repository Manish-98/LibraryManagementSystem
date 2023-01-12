package com.personal.librarymanagementsystem.controller;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class LibraryCreationRequestTest {

    @Test
    void shouldConvertToLibraryWithId() {
        Address address = new AddressBuilder("100000").build();
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LibraryCreationRequest libraryCreationRequest = new LibraryCreationRequest("library", address);
        Library expectedLibrary = new Library(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), "library", new AddressBuilder("100000").build());

        Library library = libraryCreationRequest.toLibrary(id);

        Assertions.assertEquals(expectedLibrary, library);
    }
}

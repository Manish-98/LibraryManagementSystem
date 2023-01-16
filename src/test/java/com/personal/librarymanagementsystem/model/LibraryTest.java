package com.personal.librarymanagementsystem.model;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import com.personal.librarymanagementsystem.builder.LibraryBuilder;
import com.personal.librarymanagementsystem.controller.payload.LibraryUpdationRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryTest {
    @Test
    void shouldUpdateLibraryName() {
        UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Address address = new AddressBuilder("200002").build();
        LibraryUpdationRequest libraryUpdationRequest = new LibraryUpdationRequest("City Library", address);
        Library library = new LibraryBuilder(uuid).build();
        LocalDateTime updatedAt = LocalDateTime.of(2023, 1, 5, 5, 0);

        Library expectedLibrary = new LibraryBuilder(uuid).withName("City Library").withAddress(address).withUpdationDateTime(updatedAt).build();

        Library updatedLibrary = library.updateWith(libraryUpdationRequest, updatedAt);

        assertEquals(expectedLibrary, updatedLibrary);
    }

    @Test
    void shouldUpdateLibraryAddress() {
        UUID uuid = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Address requestAddress = new AddressBuilder("400001").withCountry("Some country").build();
        LibraryUpdationRequest libraryUpdationRequest = new LibraryUpdationRequest(null, requestAddress);
        LocalDateTime updatedAt = LocalDateTime.of(2023, 1, 5, 5, 0);
        Library expectedLibrary = new LibraryBuilder(uuid).withAddress(requestAddress).withUpdationDateTime(updatedAt).build();
        Library library = new LibraryBuilder(uuid).build();

        Library updatedLibrary = library.updateWith(libraryUpdationRequest, updatedAt);

        assertEquals(expectedLibrary, updatedLibrary);
    }
}

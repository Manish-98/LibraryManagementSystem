package com.personal.librarymanagementsystem.controller;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import com.personal.librarymanagementsystem.builder.LibraryBuilder;
import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

class LibraryCreationRequestTest {

    @Test
    void shouldConvertToLibraryWithId() {
        Address address = new AddressBuilder("100000").build();
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LibraryCreationRequest libraryCreationRequest = new LibraryCreationRequest("library", address);
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);
        Library expectedLibrary = new LibraryBuilder(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .withName("library")
                .withAddress(new AddressBuilder("100000").build())
                .withCreationDateTime(localDateTime)
                .withUpdationDateTime(localDateTime)
                .build();

        Library library = libraryCreationRequest.toLibrary(id, LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC));

        Assertions.assertEquals(expectedLibrary, library);
    }
}

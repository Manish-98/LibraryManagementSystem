package com.personal.librarymanagementsystem.controller;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.service.LibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LibraryControllerTest {

    @Test
    void shouldSaveLibrary() {
        LibraryService libraryService = mock(LibraryService.class);
        LibraryController libraryController = new LibraryController(libraryService);
        LibraryCreationRequest libraryCreationRequest = new LibraryCreationRequest("library", new AddressBuilder("100000").build());
        Library expectedLibrary = new Library(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), "", new AddressBuilder("100000").build());

        when(libraryService.save(any())).thenReturn(expectedLibrary);

        Library library = libraryController.save(libraryCreationRequest);

        Assertions.assertEquals(expectedLibrary, library);
    }
}

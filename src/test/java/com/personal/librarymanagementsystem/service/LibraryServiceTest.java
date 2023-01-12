package com.personal.librarymanagementsystem.service;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.repository.LibraryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class LibraryServiceTest {

    @Test
    void saveLibrary() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        String name = "library";
        Address address = new AddressBuilder("100001").build();
        mockStatic(UUID.class);
        when(UUID.randomUUID()).thenReturn(id);
        LibraryRepository libraryRepository = mock(LibraryRepository.class);
        LibraryService libraryService = new LibraryService(libraryRepository);
        LibraryCreationRequest libraryCreationRequest = new LibraryCreationRequest(name, address);
        Library expected = new Library(id, name, address);

        when(libraryRepository.save(any())).thenReturn(expected);

        Library actual = libraryService.save(libraryCreationRequest);
        Assertions.assertEquals(expected, actual);
    }
}

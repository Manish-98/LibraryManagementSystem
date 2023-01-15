package com.personal.librarymanagementsystem.service;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import com.personal.librarymanagementsystem.builder.LibraryBuilder;
import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.controller.payload.LibraryUpdationRequest;
import com.personal.librarymanagementsystem.exception.LibraryNotFoundException;
import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.repository.LibraryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
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
        Clock clock = Clock.fixed(Instant.parse("2023-01-01T00:00:00.00Z"), ZoneOffset.UTC);
        LibraryService libraryService = new LibraryService(clock, libraryRepository);
        LibraryCreationRequest libraryCreationRequest = new LibraryCreationRequest(name, address);
        Library expected = new Library(id, name, address, LocalDateTime.of(2023, 1, 1, 0, 0));

        when(libraryRepository.save(any())).thenReturn(expected);

        Library actual = libraryService.save(libraryCreationRequest);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readAllLibraries() {
        UUID uuidOne = UUID.randomUUID();
        Library libraryOne = new LibraryBuilder(uuidOne).build();
        UUID uuidTwo = UUID.randomUUID();
        Library libraryTwo = new LibraryBuilder(uuidTwo).build();
        List<Library> expectedLibraries = List.of(libraryOne, libraryTwo);

        LibraryRepository libraryRepository = mock(LibraryRepository.class);
        Clock clock = Clock.fixed(Instant.parse("2023-01-01T00:00:00.00Z"), ZoneOffset.UTC);
        LibraryService libraryService = new LibraryService(clock, libraryRepository);

        when(libraryRepository.findAll()).thenReturn(expectedLibraries);

        List<Library> libraries = libraryService.getAll();

        Assertions.assertEquals(expectedLibraries, libraries);
    }

    @Test
    void throwExceptionIfLibraryDoesNotExist() {
        LibraryUpdationRequest libraryUpdationRequest = new LibraryUpdationRequest("Central Library", null);
        LibraryRepository libraryRepository = mock(LibraryRepository.class);
        Clock clock = Clock.fixed(Instant.parse("2023-01-01T00:00:00.00Z"), ZoneOffset.UTC);
        LibraryService libraryService = new LibraryService(clock, libraryRepository);
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        when(libraryRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(LibraryNotFoundException.class, () -> libraryService.update(id, libraryUpdationRequest));
    }

    @Test
    void returnLibraryAfterUpdating() throws LibraryNotFoundException {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Library library = new LibraryBuilder(id).withName("Default Library").build();
        Library updatedLibrary = new LibraryBuilder(id).withName("Central Library").build();
        LibraryUpdationRequest libraryUpdationRequest = new LibraryUpdationRequest("Central Library", null);

        LibraryRepository libraryRepository = mock(LibraryRepository.class);
        Clock clock = Clock.fixed(Instant.parse("2023-01-01T00:00:00.00Z"), ZoneOffset.UTC);
        LibraryService libraryService = new LibraryService(clock, libraryRepository);

        when(libraryRepository.findById(any())).thenReturn(Optional.of(library));
        when(libraryRepository.save(any())).thenReturn(updatedLibrary);

        assertEquals(updatedLibrary, libraryService.update(id, libraryUpdationRequest));
    }

    @Test
    void updateLibraryIfAlreadyExists() throws LibraryNotFoundException {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Library library = new LibraryBuilder(id).withName("Default Library").build();
        Library updatedLibrary = new LibraryBuilder(id).withName("Central Library").build();
        LibraryUpdationRequest libraryUpdationRequest = new LibraryUpdationRequest("Central Library", null);

        LibraryRepository libraryRepository = mock(LibraryRepository.class);
        Clock clock = Clock.fixed(Instant.parse("2023-01-01T00:00:00.00Z"), ZoneOffset.UTC);
        LibraryService libraryService = new LibraryService(clock, libraryRepository);

        when(libraryRepository.findById(any())).thenReturn(Optional.of(library));
        when(libraryRepository.save(any())).thenReturn(updatedLibrary);

        libraryService.update(id, libraryUpdationRequest);
        verify(libraryRepository).save(updatedLibrary);
    }
}

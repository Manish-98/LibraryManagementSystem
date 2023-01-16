package com.personal.librarymanagementsystem.service;

import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.controller.payload.LibraryUpdationRequest;
import com.personal.librarymanagementsystem.exception.LibraryNotFoundException;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LibraryService {

    private final Clock clock;
    private final LibraryRepository libraryRepository;

    public LibraryService(Clock clock, LibraryRepository libraryRepository) {
        this.clock = clock;
        this.libraryRepository = libraryRepository;
    }

    public Library save(LibraryCreationRequest libraryCreationRequest) {
        UUID id = UUID.randomUUID();
        Library library = libraryCreationRequest.toLibrary(id, LocalDateTime.ofInstant(clock.instant(), ZoneOffset.UTC));
        return libraryRepository.save(library);
    }

    public List<Library> getAll() {
        return libraryRepository.findAll();
    }

    public Library update(UUID id, LibraryUpdationRequest libraryUpdationRequest) throws LibraryNotFoundException {
        Optional<Library> library = libraryRepository.findById(id);
        if (library.isEmpty()) throw new LibraryNotFoundException();

        Library updatedLibrary = library.get().updateWith(libraryUpdationRequest, LocalDateTime.ofInstant(clock.instant(), ZoneOffset.UTC));
        return libraryRepository.save(updatedLibrary);
    }
}

package com.personal.librarymanagementsystem.service;

import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.repository.LibraryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Library save(LibraryCreationRequest libraryCreationRequest) {
        UUID id = UUID.randomUUID();
        Library library = libraryCreationRequest.toLibrary(id);
        return libraryRepository.save(library);
    }

    public List<Library> getAll() {
        return libraryRepository.findAll();
    }
}

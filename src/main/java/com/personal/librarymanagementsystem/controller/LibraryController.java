package com.personal.librarymanagementsystem.controller;

import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lms/api/v1/library")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public Library save(@Valid  @RequestBody LibraryCreationRequest libraryCreationRequest) {
        return libraryService.save(libraryCreationRequest);
    }
}

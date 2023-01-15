package com.personal.librarymanagementsystem.controller;

import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.service.LibraryService;
import com.personal.librarymanagementsystem.validator.ValidatorGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lms/api/v1")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/library")
    public Library save(@Validated({ValidatorGroup.CreateRequestValidation.class}) @RequestBody LibraryCreationRequest libraryCreationRequest) {
        return libraryService.save(libraryCreationRequest);
    }

    @GetMapping("/libraries")
    public List<Library> getAll() {
        return libraryService.getAll();
    }
}

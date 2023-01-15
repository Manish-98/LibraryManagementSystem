package com.personal.librarymanagementsystem.controller;

import com.personal.librarymanagementsystem.controller.payload.LibraryCreationRequest;
import com.personal.librarymanagementsystem.controller.payload.LibraryUpdationRequest;
import com.personal.librarymanagementsystem.exception.LibraryNotFoundException;
import com.personal.librarymanagementsystem.model.Library;
import com.personal.librarymanagementsystem.service.LibraryService;
import com.personal.librarymanagementsystem.validator.ValidatorGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @PatchMapping("/library/{id}")
    public Library update(@PathVariable String id, @Validated({ValidatorGroup.UpdateRequestValidation.class}) @RequestBody LibraryUpdationRequest libraryUpdationRequest) throws LibraryNotFoundException {
        try {
            UUID uuid = UUID.fromString(id);
            return libraryService.update(uuid, libraryUpdationRequest);
        } catch (IllegalArgumentException e) {
            throw new LibraryNotFoundException();
        }
    }
}

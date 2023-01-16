package com.personal.librarymanagementsystem.model;

import com.personal.librarymanagementsystem.controller.payload.LibraryUpdationRequest;
import com.personal.librarymanagementsystem.utils.NullUtils;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public record Library(@BsonId UUID id, String name, Address address, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public Library updateWith(LibraryUpdationRequest libraryUpdationRequest, LocalDateTime updatedAt) {
        String name = NullUtils.getOrDefault(libraryUpdationRequest.name(), this.name);
        Address address = this.address.updateWith(libraryUpdationRequest.address());
        return new Library(id, name, address, createdAt, updatedAt);
    }
}

package com.personal.librarymanagementsystem.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public record Library(@BsonId UUID id, String name, Address address, LocalDateTime createdAt) {
}

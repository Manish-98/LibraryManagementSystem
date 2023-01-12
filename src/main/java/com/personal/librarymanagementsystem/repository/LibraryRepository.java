package com.personal.librarymanagementsystem.repository;

import com.personal.librarymanagementsystem.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LibraryRepository extends MongoRepository<Library, UUID> { }

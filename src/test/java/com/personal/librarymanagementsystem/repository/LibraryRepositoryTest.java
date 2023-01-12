package com.personal.librarymanagementsystem.repository;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.UUID;

@Testcontainers
@DataMongoTest
class LibraryRepositoryTest {
    @Autowired
    private LibraryRepository libraryRepository;

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.2").withReuse(true);

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void saveLibrary() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Address address = new AddressBuilder("100001").withCountry("India").build();
        Library library = new Library(id, "libraryName", address);

        libraryRepository.save(library);

        Assertions.assertEquals(List.of(library), libraryRepository.findAll());
    }

    @Test
    void shouldNotSaveTwoLibrariesWithSameId() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Address address = new AddressBuilder("100001").withCountry("India").build();
        Library libraryOne = new Library(id, "libraryOne", address);
        Library libraryTwo = new Library(id, "libraryTwo", address);

        libraryRepository.save(libraryOne);
        libraryRepository.save(libraryTwo);

        Assertions.assertEquals(List.of(libraryTwo), libraryRepository.findAll());
    }
}

package com.personal.librarymanagementsystem.builder;

import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;

import java.time.LocalDateTime;
import java.util.UUID;

public class LibraryBuilder {
    private final UUID id;
    private String name;
    private Address address;
    private LocalDateTime createdAt;

    public LibraryBuilder(UUID id) {
        this.id = id;
        this.name = "Default Library";
        this.address = new AddressBuilder("100000").build();
        this.createdAt = LocalDateTime.of(2023, 1, 1, 0, 0);
    }

    public LibraryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LibraryBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public LibraryBuilder withCreationDateTime(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Library build() {
        return new Library(id, name, address, createdAt);
    }
}

package com.personal.librarymanagementsystem.builder;

import com.personal.librarymanagementsystem.model.Address;
import com.personal.librarymanagementsystem.model.Library;

import java.util.UUID;

public class LibraryBuilder {
    private UUID id;
    private String name;
    private Address address;

    public LibraryBuilder(UUID id) {
        this.id = id;
        this.name = "Default Library";
        this.address = new AddressBuilder("100000").build();
    }

    public LibraryBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LibraryBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public Library build() {
        return new Library(id, name, address);
    }
}

package com.personal.librarymanagementsystem.builder;

import com.personal.librarymanagementsystem.model.Address;

public class AddressBuilder {
    private String street;
    private String city;
    private String state;
    private String country;
    private final String zipCode;

    public AddressBuilder(String zipCode) {
        street = null;
        city = null;
        state = null;
        country = null;
        this.zipCode = zipCode;
    }

    public AddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public Address build() {
        return new Address(street, city, state, country, zipCode);
    }
}

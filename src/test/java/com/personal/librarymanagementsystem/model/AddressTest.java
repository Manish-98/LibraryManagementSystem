package com.personal.librarymanagementsystem.model;

import com.personal.librarymanagementsystem.builder.AddressBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressTest {
    @Test
    void shouldNotUpdateIfRequestIsNull() {
        Address existingAddress = new AddressBuilder("100001").withCity("Some city").build();

        Address address = existingAddress.updateWith(null);

        assertEquals(existingAddress, address);
    }

    @Test
    void shouldUpdateStreet() {
        Address existingAddress = new AddressBuilder("100001").withCity("Some city").build();
        Address requestAddress = new AddressBuilder(null).withStreet("Some street").build();
        Address expectedAddress = new AddressBuilder("100001").withStreet("Some street").withCity("Some city").build();

        Address updatedAddress = existingAddress.updateWith(requestAddress);

        assertEquals(expectedAddress, updatedAddress);
    }

    @Test
    void shouldUpdateCity() {
        Address existingAddress = new AddressBuilder("100001").withCity("Some city").build();
        Address requestAddress = new AddressBuilder(null).withCity("Updated city").build();
        Address expectedAddress = new AddressBuilder("100001").withCity("Updated city").build();

        Address updatedAddress = existingAddress.updateWith(requestAddress);

        assertEquals(expectedAddress, updatedAddress);
    }

    @Test
    void shouldUpdateState() {
        Address existingAddress = new AddressBuilder("100001").withCity("Some city").build();
        Address requestAddress = new AddressBuilder(null).withState("Some state").build();
        Address expectedAddress = new AddressBuilder("100001").withCity("Some city").withState("Some state").build();

        Address updatedAddress = existingAddress.updateWith(requestAddress);

        assertEquals(expectedAddress, updatedAddress);
    }

    @Test
    void shouldUpdateCountry() {
        Address existingAddress = new AddressBuilder("100001").withCity("Some city").build();
        Address requestAddress = new AddressBuilder(null).withCountry("Some country").build();
        Address expectedAddress = new AddressBuilder("100001").withCity("Some city").withCountry("Some country").build();

        Address updatedAddress = existingAddress.updateWith(requestAddress);

        assertEquals(expectedAddress, updatedAddress);
    }

    @Test
    void shouldUpdateZipCode() {
        Address existingAddress = new AddressBuilder("100001").withCity("Some city").build();
        Address requestAddress = new AddressBuilder("300003").build();
        Address expectedAddress = new AddressBuilder("300003").withCity("Some city").build();

        Address updatedAddress = existingAddress.updateWith(requestAddress);

        assertEquals(expectedAddress, updatedAddress);
    }
}

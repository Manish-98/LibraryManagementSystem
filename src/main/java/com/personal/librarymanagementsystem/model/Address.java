package com.personal.librarymanagementsystem.model;

import com.personal.librarymanagementsystem.utils.NullUtils;
import com.personal.librarymanagementsystem.validator.NullOrNotBlank;
import com.personal.librarymanagementsystem.validator.ValidatorGroup;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record Address(
        @NullOrNotBlank(message = "Street should not be blank")
        String street,

        @NullOrNotBlank(message = "City should not be blank")
        String city,

        @NullOrNotBlank(message = "State should not be blank")
        String state,

        @NullOrNotBlank(message = "Country should not be blank")
        String country,

        @NotNull(message = "Zipcode should not be blank", groups = {ValidatorGroup.CreateRequestValidation.class})
        @Pattern(regexp = "^\\d{6}$", message = "Invalid zip code")
        String zipCode
) {
    public Address updateWith(Address address) {
        if (address == null) return this;

        String street = NullUtils.getOrDefault(address.street(), this.street);
        String city = NullUtils.getOrDefault(address.city(), this.city);
        String state = NullUtils.getOrDefault(address.state(), this.state);
        String country = NullUtils.getOrDefault(address.country(), this.country);
        String zipCode = NullUtils.getOrDefault(address.zipCode(), this.zipCode);
        return new Address(street, city, state, country, zipCode);
    }
}

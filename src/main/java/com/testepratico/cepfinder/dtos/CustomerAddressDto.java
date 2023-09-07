package com.testepratico.cepfinder.dtos;

import jakarta.validation.constraints.NotBlank;

public record CustomerAddressDto(@NotBlank String email, @NotBlank String street, @NotBlank String number, @NotBlank String neighborhood, @NotBlank String city, @NotBlank String state) {

}

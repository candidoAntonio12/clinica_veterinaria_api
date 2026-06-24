package com.api.clinicaveterinaria.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDto( @NotBlank String name, @NotBlank String email, @NotBlank String password ) {

}

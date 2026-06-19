package com.api.clinicaveterinaria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TutorDto( @NotBlank String name, @NotBlank @Email String email, @NotBlank String telephone, String address) {

}

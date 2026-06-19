package com.api.clinicaveterinaria.dto;

import jakarta.validation.constraints.NotBlank;

public record AnimalDto( @NotBlank String name, @NotBlank String specie ) {

}

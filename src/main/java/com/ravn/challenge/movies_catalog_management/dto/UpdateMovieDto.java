package com.ravn.challenge.movies_catalog_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMovieDto extends MovieDto{
    @Schema(example = "1", minimum = "1")
    @NotNull(message = "The Id is required.")
    private Long id;
}

package com.ravn.challenge.movies_catalog_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDto {
    @Schema(example = "1", minimum = "1")
    Long id;

    @Schema(example = "Action")
    String name;
}

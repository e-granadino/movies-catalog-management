package com.ravn.challenge.movies_catalog_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {

    @Min(value = 0L, message = "The movieId must be positive")
    @Schema(example = "1", minimum = "1")
    private Long movieId;

    @Min(value = 1L, message = "The rating must be between 1 and 10")
    @Max(value = 10L, message = "The rating must be between 1 and 10")
    @Schema(example = "9", minimum = "1", maximum = "10")
    private Integer rate;

    @NotNull(message = "The synopsis is required.")
    @NotBlank(message = "The synopsis is required.")
    @Schema(example = "It was an excellent movie")
    private String comment;
}

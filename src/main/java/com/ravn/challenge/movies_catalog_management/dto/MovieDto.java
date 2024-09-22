package com.ravn.challenge.movies_catalog_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MovieDto {
    @NotNull(message = "The title is required.")
    @NotBlank(message = "The title is required.")
    @Schema(example = "The Great Adventure")
    private String title;

    @Schema(example = "https://storage/v1/1727001740283_testimage.jpg")
    private String moviePoster;

    @NotNull(message = "The synopsis is required.")
    @NotBlank(message = "The synopsis is required.")
    @Schema(example = "A thrilling journey across uncharted territories.")
    private String synopsis;

    @NotNull(message = "The release year is required.")
    @Min(value = 0L, message = "The release year must be positive")
    @Schema(example = "2021")
    private Integer releaseYear;

    @NotNull(message = "The director is required.")
    @NotBlank(message = "The director is required.")
    @Schema(example = "John Smith")
    private String director;

    @NotNull(message = "The duration is required.")
    @Min(value = 0L, message = "The release year must be positive")
    @Schema(example = "120")
    private Integer duration;

    @Schema(hidden = true)
    private LocalDateTime createdAt;

    @Schema(hidden = true)
    private String createdBy;

    @Schema(hidden = true)
    private LocalDateTime updatedAt;

    @Schema(hidden = true)
    private String updatedBy;

    private List<GenreDto> genres = new ArrayList<>();
}

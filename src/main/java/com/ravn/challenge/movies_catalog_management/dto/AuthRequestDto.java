package com.ravn.challenge.movies_catalog_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequestDto {
    @NotNull(message = "The username is required.")
    @NotBlank(message = "The username is required.")
    @Schema(example = "alice_brown")
    private String username;

    @NotBlank(message = "The password is required.")
    @NotNull(message = "The password is required.")
    @Schema(example = "alice")
    private String password;
}

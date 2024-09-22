package com.ravn.challenge.movies_catalog_management.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "The email is required.")
    @NotBlank(message = "The email is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    @Schema(example = "user@email.com")
    private String email;

    @NotNull(message = "The firstName is required.")
    @NotBlank(message = "The username is required.")
    @Schema(example = "John")
    private String firstName;

    @NotNull(message = "The lastName is required.")
    @NotBlank(message = "The lastName is required.")
    @Schema(example = "Doe")
    private String lastName;

    @NotNull(message = "The userName is required.")
    @NotBlank(message = "The userName is required.")
    @Schema(example = "john_doe")
    private String userName;

    @NotNull(message = "The password is required.")
    @NotBlank(message = "The password is required.")
    @Schema(example = "Ab1234")
    private String password;

}

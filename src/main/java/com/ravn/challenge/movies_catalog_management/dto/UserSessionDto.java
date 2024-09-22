package com.ravn.challenge.movies_catalog_management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSessionDto extends UserDto {
    private String role;
}

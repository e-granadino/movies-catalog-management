package com.ravn.challenge.movies_catalog_management.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class UserSessionDto extends UserDto {
    private String role;
}

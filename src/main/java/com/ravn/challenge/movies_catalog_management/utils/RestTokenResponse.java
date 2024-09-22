package com.ravn.challenge.movies_catalog_management.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestTokenResponse {
    private String message;

    private String status;

    private Integer statusCode;

    private String token;
}

package com.ravn.challenge.movies_catalog_management.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int statusCode;
    private String status;
    private String message;

    public ErrorResponse(int statusCode, String message, String status) {
        this.statusCode = statusCode;
        this.message = message;
        this.status = status;
    }
}

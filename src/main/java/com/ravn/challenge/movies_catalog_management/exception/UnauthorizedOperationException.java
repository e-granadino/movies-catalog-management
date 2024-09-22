package com.ravn.challenge.movies_catalog_management.exception;

public class UnauthorizedOperationException extends RuntimeException{
    public UnauthorizedOperationException(String message){
        super(message);
    }
}

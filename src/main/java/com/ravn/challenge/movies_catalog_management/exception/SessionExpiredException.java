package com.ravn.challenge.movies_catalog_management.exception;

public class SessionExpiredException extends RuntimeException{
    public SessionExpiredException(String message){
        super(message);
    }
}

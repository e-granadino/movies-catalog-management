package com.ravn.challenge.movies_catalog_management.exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(Long id){
        super("The movie with ID: " + id + " was not found in the database");
    }
}

package com.ravn.challenge.movies_catalog_management.exception;

public class InvalidUpdateOperationException extends RuntimeException{
    public InvalidUpdateOperationException(String movieName){
        super("The id for the movie: " + movieName + " was not provided");
    }
}

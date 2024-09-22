package com.ravn.challenge.movies_catalog_management.exception;

public class InvalidDeleteOperationException extends RuntimeException{
    public InvalidDeleteOperationException(Long id){
        super("Wrong or Missing ID: " + id + ". Check the id is a valid one and exists on the database.");
    }
}

package com.ravn.challenge.movies_catalog_management.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userName){
        super("The User for the username: " + userName + " was not found on the database.");
    }
}

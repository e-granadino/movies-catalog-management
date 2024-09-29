package com.ravn.challenge.movies_catalog_management.exception;

public class UploadImageException extends RuntimeException{
    public UploadImageException(String error){
        super(error);
    }
}

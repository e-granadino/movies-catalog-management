package com.ravn.challenge.movies_catalog_management.exception;

import com.ravn.challenge.movies_catalog_management.utils.Constants;
import com.ravn.challenge.movies_catalog_management.utils.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), Constants.FAIL_RESPONSE);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMovieNotFoundException(MovieNotFoundException ex) {
        ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), Constants.FAIL_RESPONSE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(SessionExpiredException.class)
    public ResponseEntity<ErrorResponse> handleSessionExpiredException(SessionExpiredException ex) {
        ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage(), Constants.FAIL_RESPONSE);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedOperationException(UnauthorizedOperationException ex) {
        ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), Constants.FAIL_RESPONSE);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(InvalidUpdateOperationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUpdateOperationException(InvalidUpdateOperationException ex) {
        ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), Constants.FAIL_RESPONSE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidDeleteOperationException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDeleteOperationException(InvalidDeleteOperationException ex) {
        ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), Constants.FAIL_RESPONSE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse =  new ErrorResponse();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setStatus(Constants.FAIL_RESPONSE);
        errorResponse.setMessage("Check the following errors: "+errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}

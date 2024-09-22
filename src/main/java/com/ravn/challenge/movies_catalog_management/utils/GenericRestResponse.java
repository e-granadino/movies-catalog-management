package com.ravn.challenge.movies_catalog_management.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericRestResponse<T> {
    /**The error message sent advising about the error*/
    private String message;

    /**The status: Success or Fail*/
    private String status;

    /**
     * 1xx Informational. This class of status code indicates a provisional response
     * 2xx Success. ...
     * 3xx Redirection. ...
     * 4xx Client Error. ...
     * 5xx Server Error.
     *
     */
    private Integer statusCode;

    private T response;

    GenericRestResponse(Builder<T> builder){
        this.status = builder.status;
        this.response = builder.response;
        this.statusCode = builder.statusCode;
        this.message = builder.message;
    }

    public static class Builder<T> {
        private String message;
        private String status;
        private Integer statusCode;
        private T response;

        public Builder<T> status(String status){
            this.status = status;
            return this;
        }

        public Builder<T> response(T response){
            this.response = response;
            return this;
        }

        public Builder<T> statusCode(Integer statusCode){
            this.statusCode = statusCode;
            return this;
        }

        public Builder<T> message(String message){
            this.message = message;
            return this;
        }

        public GenericRestResponse<T> build(){
            return new GenericRestResponse<>(this);
        }
    }
}

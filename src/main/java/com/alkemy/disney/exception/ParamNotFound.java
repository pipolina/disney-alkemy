package com.alkemy.disney.exception;

public class ParamNotFound extends RuntimeException{
    public ParamNotFound(String param) {
        super("Error, " + param + " is not found.");
    }
}

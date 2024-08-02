package com.bmc.exception;

public class MovieNotFound extends Exception {
    public MovieNotFound(String message) {
        super(message);
    }
}
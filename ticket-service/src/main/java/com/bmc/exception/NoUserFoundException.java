package com.bmc.exception;

public class NoUserFoundException extends Exception {
    public NoUserFoundException(String message) {
        super(message);
    }
}
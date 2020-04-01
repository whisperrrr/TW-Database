package com.thoughtworks.exception;

public class WrongFormatException extends Exception {
    public WrongFormatException() {
    }

    public WrongFormatException(String message) {
        super(message);
    }
}

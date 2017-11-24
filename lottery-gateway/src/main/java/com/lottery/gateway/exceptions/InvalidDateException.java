package com.lottery.gateway.exceptions;

public class InvalidDateException extends Exception {
    public InvalidDateException(String message) {
        super(message);
    }

    public InvalidDateException(Throwable cause) {
        super(cause);
    }

    public InvalidDateException(String message, Throwable cause) {
        super(message, cause);
    }

}

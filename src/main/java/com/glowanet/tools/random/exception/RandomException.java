package com.glowanet.tools.random.exception;

public class RandomException extends RuntimeException {

    public RandomException() {
        super();
    }

    public RandomException(String message) {
        super(message);
    }

    RandomException(Throwable cause) {
        super(cause);
    }
}

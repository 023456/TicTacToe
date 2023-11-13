package org.example.Exceptions;

public class GameInvalidationException extends Exception{

    public GameInvalidationException() {
    }

    public GameInvalidationException(String message) {
        super(message);
    }

    public GameInvalidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameInvalidationException(Throwable cause) {
        super(cause);
    }

    public GameInvalidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

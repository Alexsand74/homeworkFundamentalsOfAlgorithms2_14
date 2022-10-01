package org.example;

public class FullRepositoryException extends RuntimeException {
    public FullRepositoryException() {
    }

    public FullRepositoryException(String message) {
        super(message);
    }

    public FullRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FullRepositoryException(Throwable cause) {
        super(cause);
    }

    public FullRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

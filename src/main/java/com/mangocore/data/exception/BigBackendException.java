package com.mangocore.data.exception;

/**
 * Created by notreami on 17/9/10.
 */
public class BigBackendException extends RuntimeException {
    public BigBackendException() {
        super();
    }

    public BigBackendException(String message) {
        super(message);
    }

    public BigBackendException(Throwable cause) {
        super(cause);
    }

    public BigBackendException(String message, Throwable cause) {
        super(message, cause);
    }

    protected BigBackendException(String message, Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

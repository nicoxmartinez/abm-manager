package com.ventas.abm.manager.exceptions;

public class CodigoException extends RuntimeException {

    public CodigoException(String message) {
        super(message);
    }

    public CodigoException() {
    }

    public CodigoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodigoException(Throwable cause) {
        super(cause);
    }

    public CodigoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

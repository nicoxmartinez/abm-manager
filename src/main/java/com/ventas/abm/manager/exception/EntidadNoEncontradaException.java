package com.ventas.abm.manager.exception;

import jakarta.persistence.EntityNotFoundException;

public class EntidadNoEncontradaException extends EntityNotFoundException {

    public EntidadNoEncontradaException() {
    }

    public EntidadNoEncontradaException(Exception cause) {
        super(cause);
    }

    public EntidadNoEncontradaException(String message) {
        super(message);
    }

    public EntidadNoEncontradaException(String message, Exception cause) {
        super(message, cause);
    }

}

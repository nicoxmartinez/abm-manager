package com.ventas.abm.manager.exception;

import jakarta.persistence.EntityNotFoundException;

public class EntidadNoEncontradaException extends EntityNotFoundException {

	private static final long serialVersionUID = 1L;

	public EntidadNoEncontradaException() {
		super();
	}

	public EntidadNoEncontradaException(Exception cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public EntidadNoEncontradaException(String message, Exception cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EntidadNoEncontradaException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

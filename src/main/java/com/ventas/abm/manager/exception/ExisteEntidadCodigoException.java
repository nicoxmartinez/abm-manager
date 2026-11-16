package com.ventas.abm.manager.exception;

import jakarta.persistence.EntityExistsException;

public class ExisteEntidadCodigoException extends EntityExistsException {

	private static final long serialVersionUID = 1L;

	public ExisteEntidadCodigoException() {
		super();
	}

	public ExisteEntidadCodigoException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExisteEntidadCodigoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ExisteEntidadCodigoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}	

}

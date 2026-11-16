package com.ventas.abm.manager.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.dao.DataIntegrityViolationException;

public class ErrorIntegridadException extends DataIntegrityViolationException {

	private static final long serialVersionUID = 1L;

	public ErrorIntegridadException(@Nullable String msg) {
		super(msg);
	}

	public ErrorIntegridadException(@Nullable String msg, @Nullable Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

}

package com.ventas.abm.manager.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.dao.DataIntegrityViolationException;

public class ErrorIntegridadException  extends DataIntegrityViolationException {

    public ErrorIntegridadException(@Nullable String msg) {
        super(msg);
    }

    public ErrorIntegridadException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

}

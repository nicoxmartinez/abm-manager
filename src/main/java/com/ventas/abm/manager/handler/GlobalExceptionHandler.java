package com.ventas.abm.manager.handler;

import com.ventas.abm.manager.exception.EntidadNoEncontradaException;
import com.ventas.abm.manager.exception.ErrorIntegridadException;
import com.ventas.abm.manager.exception.ErrorValorNuloException;
import com.ventas.abm.manager.model.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.channels.NonWritableChannelException;
import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadNoEncontradaException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntidadNoEncontradaException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
            errorDto.setTimestamp(LocalDateTime.now());
            errorDto.setStatus(HttpStatus.NOT_FOUND.value());
            errorDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorIntegridadException.class)
    public ResponseEntity<ErrorResponseDto> HandleErrorIntegrity(ErrorIntegridadException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorValorNuloException.class)
    public ResponseEntity<ErrorResponseDto> handlerErrorNullValue(ErrorValorNuloException e) {
        ErrorResponseDto errorDto = new ErrorResponseDto();
        errorDto.setTimestamp(LocalDateTime.now());
        errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setMessage(e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

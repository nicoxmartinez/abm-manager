package com.ventas.abm.manager.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ventas.abm.manager.exception.EntidadNoEncontradaException;
import com.ventas.abm.manager.exception.ErrorIntegridadException;
import com.ventas.abm.manager.exception.ErrorValorNuloException;
import com.ventas.abm.manager.exception.ExisteEntidadCodigoException;
import com.ventas.abm.manager.model.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = EntidadNoEncontradaException.class, produces = "application/json")
	public ResponseEntity<ErrorResponseDto> handleEntityNotFound(EntidadNoEncontradaException e) {
		ErrorResponseDto errorDto = new ErrorResponseDto();
		errorDto.setTimestamp(LocalDateTime.now());
		errorDto.setStatus(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}

	@ExceptionHandler(value = ErrorIntegridadException.class, produces = "aplication/json")
	public ResponseEntity<ErrorResponseDto> HandleErrorIntegrity(ErrorIntegridadException e) {
		ErrorResponseDto errorDto = new ErrorResponseDto();
		errorDto.setTimestamp(LocalDateTime.now());
		errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDto.setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
	}

	@ExceptionHandler(value = ErrorValorNuloException.class, produces = "aplication/json")
	public ResponseEntity<ErrorResponseDto> handlerErrorNullValue(ErrorValorNuloException e) {
		ErrorResponseDto errorDto = new ErrorResponseDto();
		errorDto.setTimestamp(LocalDateTime.now());
		errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorDto.setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
	}

	@ExceptionHandler(value = ExisteEntidadCodigoException.class, produces = "application/json")
	public ResponseEntity<ErrorResponseDto> handleEntityExists(ExisteEntidadCodigoException e) {
		ErrorResponseDto errorDto = new ErrorResponseDto();
		errorDto.setTimestamp(LocalDateTime.now());
		errorDto.setStatus(HttpStatus.CONFLICT.value());
		errorDto.setMessage(e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
	}
}

package com.ventas.abm.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ventas.abm.manager.domain.Domicilio;
import com.ventas.abm.manager.service.DomicilioService;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {

	@Autowired
	DomicilioService service;

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarDomicilio(@PathVariable("id") Long idDomicilio) {
		try {
			service.deleteDomicilio(idDomicilio);
			return (ResponseEntity<?>) ResponseEntity.ok();
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Domicilio> obtenerDomicilioPorId(@PathVariable("id") Long idDomicilio) {
		try {
			Domicilio domicilio = service.getDomicilioById(idDomicilio);
			return ResponseEntity.ok(domicilio);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

}
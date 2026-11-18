package com.ventas.abm.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.abm.manager.domain.Domicilio;
import com.ventas.abm.manager.service.DomicilioService;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {

	@Autowired
	DomicilioService service;

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarDomicilio(@PathVariable("id") Long idDomicilio) {
		// TODO: process POST request
		service.deleteDomicilio(idDomicilio);
		return (ResponseEntity<?>) ResponseEntity.ok();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Domicilio> obtenerDomicilioPorId(@PathVariable("id") Long idDomicilio) {
		// TODO: process PUT request
		Domicilio domicilio = service.getDomicilioById(idDomicilio);
		return ResponseEntity.ok(domicilio);
	}

}
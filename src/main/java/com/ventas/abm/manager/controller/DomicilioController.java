package com.ventas.abm.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping
	public ResponseEntity<Domicilio> crearDomicilioController(@RequestBody Domicilio domicilio) {
		try {
			Domicilio nuevoDomicilio = service.createDomicilio(domicilio);
			return ResponseEntity.ok(nuevoDomicilio);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<Domicilio> modificarDomicilioController(@RequestBody Domicilio domicilio) {
		try {
			Domicilio modificarDomicilio = service.modifyDomicilio(domicilio);
			return ResponseEntity.ok(modificarDomicilio);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Domicilio> obtenerDomicilioPorId(@PathVariable Long id) {
		try {
			Domicilio domicilio = service.getDomicilioById(id);
			return ResponseEntity.ok(domicilio);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/direccion/{direccion}")
	public ResponseEntity<List<Domicilio>> obtenerDomicilioPorDireccion(@PathVariable String direccion) {
		try {
			List<Domicilio> listaDomicilios = service.getDomicilioByDireccion(direccion);
			return ResponseEntity.ok(listaDomicilios);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}

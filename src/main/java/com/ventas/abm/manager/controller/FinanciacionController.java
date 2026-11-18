package com.ventas.abm.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.abm.manager.domain.Financiacion;
import com.ventas.abm.manager.model.FinanciacionDto;
import com.ventas.abm.manager.service.FinanciacionService;

@RestController
@RequestMapping("/financiacion")
public class FinanciacionController {

	@Autowired
	FinanciacionService service;

	@PostMapping
	public ResponseEntity<Financiacion> crearFinanciacion(@RequestBody Financiacion financiacion) {
		// TODO: process POST request
		try {
			Financiacion nuevaFinanciacion = service.createFinanciacion(financiacion);
			return ResponseEntity.ok(nuevaFinanciacion);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<Financiacion> modificarFinanciacion(@RequestBody Financiacion financiacion) {
		// TODO: process PUT request
		try {
			Financiacion modificarFinanciacion = service.modifyFinanciacion(financiacion);
			return ResponseEntity.ok(modificarFinanciacion);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Financiacion> bajaFinanciacion(@PathVariable("id") Long idFinanciacion) {
		try {
			Financiacion financiacion = service.deleteFinanciacion(idFinanciacion);
			return ResponseEntity.ok(financiacion);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

	@GetMapping("/lista/activas")
	public ResponseEntity<List<FinanciacionDto>> obtenerListaDeFinanciacionActivas() {
		try {
			List<FinanciacionDto> financiaciones = service.getAllFinanciacionActiva();
			return ResponseEntity.ok(financiaciones);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Financiacion> obtenerFinanciacionPorId(@PathVariable("id") Long idFinanciacion) {
		try {
			Financiacion financiacion = service.getFinanciacionById(idFinanciacion);
			return ResponseEntity.ok(financiacion);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<FinanciacionDto> obtenerFinanciacionPorCodigo(@PathVariable("codigo") Integer codigo) {
		try {
			FinanciacionDto financiacion = service.getFinanciacionByCodigo(codigo);
			return ResponseEntity.ok(financiacion);
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

	@GetMapping("/descripcion/{descripcion}")
	public List<FinanciacionDto> obtenerFinanciacionPorDescripcion(@PathVariable("descripcion") String descripcion) {
		try {
			List<FinanciacionDto> financiaciones = service.getFinanciacionByDescripcion(descripcion);
			return financiaciones;
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
	}

}

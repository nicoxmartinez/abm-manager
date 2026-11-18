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
		Financiacion nuevaFinanciacion = service.createFinanciacion(financiacion);
		return ResponseEntity.ok(nuevaFinanciacion);
	}

	@PutMapping
	public ResponseEntity<Financiacion> modificarFinanciacion(@RequestBody Financiacion financiacion) {
		// TODO: process PUT request
		Financiacion modificarFinanciacion = service.modifyFinanciacion(financiacion);
		return ResponseEntity.ok(modificarFinanciacion);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Financiacion> bajaFinanciacion(@PathVariable("id") Long idFinanciacion) {
		Financiacion financiacion = service.deleteFinanciacion(idFinanciacion);
		return ResponseEntity.ok(financiacion);
	}

	@GetMapping("/lista/activas")
	public ResponseEntity<List<FinanciacionDto>> obtenerListaDeFinanciacionActivas() {
		List<FinanciacionDto> financiaciones = service.getAllFinanciacionActiva();
		return ResponseEntity.ok(financiaciones);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Financiacion> obtenerFinanciacionPorId(@PathVariable("id") Long idFinanciacion) {
		Financiacion financiacion = service.getFinanciacionById(idFinanciacion);
		return ResponseEntity.ok(financiacion);
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<FinanciacionDto> obtenerFinanciacionPorCodigo(@PathVariable("codigo") Integer codigo) {
		FinanciacionDto financiacion = service.getFinanciacionByCodigo(codigo);
		return ResponseEntity.ok(financiacion);
	}

	@GetMapping("/descripcion/{descripcion}")
	public List<FinanciacionDto> obtenerFinanciacionPorDescripcion(@PathVariable("descripcion") String descripcion) {
		List<FinanciacionDto> financiaciones = service.getFinanciacionByDescripcion(descripcion);
		return financiaciones;
	}

}

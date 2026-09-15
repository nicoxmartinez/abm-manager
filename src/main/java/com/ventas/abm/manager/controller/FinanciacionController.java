package com.ventas.abm.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ventas.abm.manager.domain.Financiacion;
import com.ventas.abm.manager.service.FinanciacionService;


@RestController
@RequestMapping("/financiacion")
public class FinanciacionController {

	@Autowired
	FinanciacionService service;
	
	@PostMapping
	public ResponseEntity<Financiacion> crearFinanciacion(@RequestBody Financiacion financiacion) {
		try {
			Financiacion nuevaFinanciacion = service.createFinanciacion(financiacion);
			return ResponseEntity.ok(nuevaFinanciacion);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PutMapping
	public ResponseEntity<Financiacion> modificarFinanciacion(@RequestBody Financiacion financiacion) {
		try {
			Financiacion modificarFinanciacion = service.modifyFinanciacion(financiacion);
            return ResponseEntity.ok(modificarFinanciacion);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Financiacion> bajaFinanciacion(@PathVariable Long id) {
		try {
			Financiacion financiacion = service.deleteFinanciacion(id);
			return ResponseEntity.ok(financiacion);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Financiacion>> obtenerTodoFinanciacion() {
		try {
			List<Financiacion> financiaciones = service.getAllFinanciacion();
			return ResponseEntity.ok(financiaciones);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/lista/activas")
	public ResponseEntity<List<Financiacion>> obtenerListaDeFinanciacionActivas() {
		try {
			List<Financiacion> financiaciones = service.getAllFinanciacionActiva();
			return ResponseEntity.ok(financiaciones);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Financiacion> obtenerFinanciacionPorId(@PathVariable Long id) {
		try {
			Financiacion financiacion = service.getFinanciacionById(id);
			return ResponseEntity.ok(financiacion);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Financiacion> obtenerFinanciacionPorCodigo(@PathVariable Integer codigo) {
		try {
			Financiacion financiacion = service.getFinanciacionByCodigo(codigo);
			return ResponseEntity.ok(financiacion);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/descripcion/{descripcion}")
	public List<Financiacion> obtenerFinanciacionPorDescripcion(@PathVariable String descripcion) {
		try {
			List<Financiacion> financiaciones = service.getFinanciacionByDescripcion(descripcion);
			return financiaciones;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

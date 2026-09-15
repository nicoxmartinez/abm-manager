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

import com.ventas.abm.manager.domain.Tienda;
import com.ventas.abm.manager.service.TiendaService;

@RestController
@RequestMapping("/tienda")
public class TiendaController {

	@Autowired
	TiendaService service;

	@PostMapping
	public ResponseEntity<Tienda> crearTienda(@RequestBody Tienda tienda) {
		try {
			Tienda nuevaTienda = service.createTienda(tienda);
			return ResponseEntity.ok(nuevaTienda);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<Tienda> editarMedioDePago(@RequestBody Tienda tienda) {
		try {
			Tienda modificarTienda = service.modifyTienda(tienda);
			return ResponseEntity.ok(modificarTienda);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Tienda> bajaTienda(@PathVariable Long id) {
		try {
			Tienda tienda = service.deleteTienda(id);
			return ResponseEntity.ok(tienda);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Tienda> obtenerTiendaPorId(@PathVariable Long id) {
		try {
			Tienda tienda = service.getTiendaById(id);
			return ResponseEntity.ok(tienda);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Tienda> obtenerTiendaPorCodigo(@PathVariable Integer codigo) {
		try {
			Tienda tienda = service.getTiendaByCodigo(codigo);
			return ResponseEntity.ok(tienda);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/descripcion/{descripcion}")
	public ResponseEntity<List<Tienda>> obtenerTiendaPorDescripcion(@PathVariable String descripcion) {
		try {
			List<Tienda> tiendas = service.getTiendaByDescripcion(descripcion);
			return ResponseEntity.ok(tiendas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

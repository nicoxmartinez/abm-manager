package com.ventas.abm.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@DeleteMapping("/{id}")
	public ResponseEntity<Tienda> bajaTienda(@PathVariable("id") Long idTienda) {
		try {
			Tienda tienda = service.deleteTienda(idTienda);
			return ResponseEntity.ok(tienda);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Tienda> obtenerTiendaPorId(@PathVariable("id") Long idTienda) {
		try {
			Tienda tienda = service.getTiendaById(idTienda);
			return ResponseEntity.ok(tienda);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Tienda> obtenerTiendaPorCodigo(@PathVariable("codigo") String codigo) {
		try {
			Tienda tienda = service.getTiendaByCodigo(codigo);
			return ResponseEntity.ok(tienda);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/descripcion/{descripcion}")
	public ResponseEntity<List<Tienda>> obtenerTiendaPorDescripcion(@PathVariable("descripcion") String descripcion) {
		try {
			List<Tienda> tiendas = service.getTiendaByDescripcion(descripcion);
			return ResponseEntity.ok(tiendas);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

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

import com.ventas.abm.manager.domain.Almacen;
import com.ventas.abm.manager.service.AlmacenService;

@RestController
@RequestMapping("/almacen")
public class AlmacenController {

	@Autowired
	AlmacenService service;

	@PostMapping
	public ResponseEntity<Almacen> crearAlmacen(@RequestBody Almacen almacen) {
		try {
			Almacen nuevoAlmacen = service.createAlmacen(almacen);
			return ResponseEntity.ok(nuevoAlmacen);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<Almacen> modificarAlmacen(@RequestBody Almacen almacen) {
		try {
			Almacen modificarAlmacen = service.modifyAlmacen(almacen);
			return ResponseEntity.ok(modificarAlmacen);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Almacen> bajaAlmacen(@PathVariable("id") long idAlmacen) {
		try {
			Almacen almacen = service.deleteAlmacen(idAlmacen);
			return ResponseEntity.ok(almacen);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<List<Almacen>> obtenerTodoAlmacen() {
		try {
			List<Almacen> almacenes = service.getAllAlmacen();
			return ResponseEntity.ok(almacenes);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Almacen> obtenerAlmacenPorId(@PathVariable("id") Long idAlmacen) {
		try {
			Almacen almacen = service.getAlmacenById(idAlmacen);
			return ResponseEntity.ok(almacen);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<Almacen> obtenerAlmacenPorCodigo(@PathVariable("codigo") String codigo) {
		try {
			Almacen almacen = service.getAlmacenByCodigo(codigo);
			return ResponseEntity.ok(almacen);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/descripcion/{descripcion}")
	public ResponseEntity<List<Almacen>> obtenerAlmacenPorDescripcion(@PathVariable("descripcion") String descripcion) {
		try {
			List<Almacen> almacenes = service.getAlmacenByDescripcion(descripcion);
			return ResponseEntity.ok(almacenes);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

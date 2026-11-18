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

import com.ventas.abm.manager.domain.Almacen;
import com.ventas.abm.manager.model.AlmacenDto;
import com.ventas.abm.manager.service.AlmacenService;

@RestController
@RequestMapping("/almacen")
public class AlmacenController {

	@Autowired
	AlmacenService service;

	@PostMapping
	public ResponseEntity<Almacen> crearAlmacen(@RequestBody Almacen almacen) {
		// TODO: process POST request
		Almacen nuevoAlmacen = service.createAlmacen(almacen);
		return ResponseEntity.ok(nuevoAlmacen);
	}

	@PutMapping
	public ResponseEntity<Almacen> modificarAlmacen(@RequestBody Almacen almacen) {
		// TODO: process PUT request
		Almacen modificarAlmacen = service.modifyAlmacen(almacen);
		return ResponseEntity.ok(modificarAlmacen);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Almacen> bajaAlmacen(@PathVariable("id") Long idAlmacen) {
		Almacen almacen = service.deleteAlmacen(idAlmacen);
		return ResponseEntity.ok(almacen);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Almacen> obtenerAlmacenPorId(@PathVariable("id") Long idAlmacen) {
		Almacen almacen = service.getAlmacenById(idAlmacen);
		return ResponseEntity.ok(almacen);
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<AlmacenDto> obtenerAlmacenPorCodigo(@PathVariable("codigo") String codigo) {
		AlmacenDto almacen = service.getAlmacenByCodigo(codigo);
		return ResponseEntity.ok(almacen);
	}

	@GetMapping("/descripcion/{descripcion}")
	public ResponseEntity<List<AlmacenDto>> obtenerAlmacenPorDescripcion(
			@PathVariable("descripcion") String descripcion) {
		List<AlmacenDto> almacenes = service.getAlmacenByDescripcion(descripcion);
		return ResponseEntity.ok(almacenes);

	}

}

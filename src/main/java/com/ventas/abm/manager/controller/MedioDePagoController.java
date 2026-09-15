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

import com.ventas.abm.manager.domain.MedioDePago;
import com.ventas.abm.manager.service.MedioDePagoService;

@RestController
@RequestMapping("/medioDePago")
public class MedioDePagoController {

	@Autowired
	MedioDePagoService service;

	@PostMapping
	public ResponseEntity<MedioDePago> crearMedioDePago(@RequestBody MedioDePago medioDePago) {
		try {
			MedioDePago nuevoMedioDePago = service.createMedioDePago(medioDePago);
			return ResponseEntity.ok(nuevoMedioDePago);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<MedioDePago> editarMedioDePago(@RequestBody MedioDePago medioDePago) {
		try {
			MedioDePago modificarMedioDePago = service.modifyMedioDePago(medioDePago);
			return ResponseEntity.ok(modificarMedioDePago);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<MedioDePago> bajaMedioDePago(@PathVariable Long id) {
		try {
			MedioDePago medioDePago = service.deleteMedioDePago(id);
			return ResponseEntity.ok(medioDePago);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/lista/habilitados")
	public ResponseEntity<List<MedioDePago>> obtenerListaMedioDePagoHabilitados() {
		try {
			List<MedioDePago> mediosDePago = service.getAllMedioDePagoHabilitados();
			return ResponseEntity.ok(mediosDePago);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<MedioDePago> obtenerMedioDePagoPorId(@PathVariable Long id) {
		try {
			MedioDePago medioDePago = service.getMedioDePagoById(id);
			return ResponseEntity.ok(medioDePago);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/codigo/{codigo}")
	public ResponseEntity<MedioDePago> obtenerMedioDePagoPorCodigo(@PathVariable Integer codigo) {
		try {
			MedioDePago medioDePago = service.getMedioDePagoByCodigo(codigo);
			return ResponseEntity.ok(medioDePago);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/detalle/{detalle}")
	public ResponseEntity<List<MedioDePago>> obtenerMedioDePagoPorDetalle(@PathVariable String detalle) {
		try {
			List<MedioDePago> mediosDePago = service.getMedioDePagoByDetalle(detalle);
			return ResponseEntity.ok(mediosDePago);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

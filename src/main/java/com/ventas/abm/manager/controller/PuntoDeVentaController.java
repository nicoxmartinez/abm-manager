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

import com.ventas.abm.manager.domain.PuntoDeVenta;
import com.ventas.abm.manager.service.PuntoDeVentaService;

@RestController
@RequestMapping("/puntoDeVenta")
public class PuntoDeVentaController {

	@Autowired
	PuntoDeVentaService service;

	@PostMapping
	public ResponseEntity<PuntoDeVenta> crearPuntoDeVenta(@RequestBody PuntoDeVenta puntoDeVenta) {
		try {
			PuntoDeVenta nuevoPuntoDeVenta = service.createPuntoDeVenta(puntoDeVenta);
			return ResponseEntity.ok(nuevoPuntoDeVenta);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<PuntoDeVenta> modificarPuntoDeVenta(@RequestBody PuntoDeVenta puntoDeVenta) {
		try {
			PuntoDeVenta modificarPuntoDeVenta = service.modifyPuntoDeVenta(puntoDeVenta);
			return ResponseEntity.ok(modificarPuntoDeVenta);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<PuntoDeVenta> obtenerPuntoDeVentaPorId(@PathVariable Long id) {
		try {
			PuntoDeVenta puntoDeVenta = service.getPuntoDeVentaById(id);
			return ResponseEntity.ok(puntoDeVenta);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/numero/{numero}")
	public ResponseEntity<PuntoDeVenta> obtenerPuntoDeVentaPorNumero(@PathVariable Integer numero) {
		try {
			PuntoDeVenta puntoDeVenta = service.getPuntoDeVentaByNumero(numero);
			return ResponseEntity.ok(puntoDeVenta);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<List<PuntoDeVenta>> obtenerPuntoDeVentaPorNombre(@PathVariable String nombre) {
		try {
			List<PuntoDeVenta> puntosDeVenta = service.getPuntoDeVentaByNombre(nombre);
			return ResponseEntity.ok(puntosDeVenta);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

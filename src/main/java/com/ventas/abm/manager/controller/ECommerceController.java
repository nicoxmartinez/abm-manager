package com.ventas.abm.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ventas.abm.manager.domain.ECommerce;
import com.ventas.abm.manager.service.ECommerceService;

@RestController
@RequestMapping("/ecommerce")
public class ECommerceController {

	@Autowired
	ECommerceService ecommerceService;

	@PostMapping()
	public ResponseEntity<ECommerce> crearECommerce(@RequestBody ECommerce ecommerce) {
		// TODO: process POST request
		try {
			ECommerce nuevoECommerce = ecommerceService.createECommerce(ecommerce);
			return ResponseEntity.ok(nuevoECommerce);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping()
	public ResponseEntity<ECommerce> modificarECommerce(@RequestBody ECommerce ecommerce) {
		// TODO: process PUT request
		try {
			ECommerce ecommerceModificado = ecommerceService.modifyECommerce(ecommerce);
			return ResponseEntity.ok(ecommerceModificado);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

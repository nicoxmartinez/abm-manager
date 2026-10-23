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

import com.ventas.abm.manager.domain.Local;
import com.ventas.abm.manager.service.LocalService;

@RestController
@RequestMapping("/Local")
public class LocalController {

	@Autowired
	LocalService localService;

	@PostMapping()
	public ResponseEntity<Local> crearLocal(@RequestBody Local local) {
		// TODO: process POST request
		try {
			Local nuevoLocal = localService.createLocal(local);
			return ResponseEntity.ok(nuevoLocal);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping()
	public ResponseEntity<Local> modificarLocal(@RequestBody Local local) {
		// TODO: process PUT request
		try {
			Local modificarLocal = localService.modifyLocal(local);
			return ResponseEntity.ok(modificarLocal);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

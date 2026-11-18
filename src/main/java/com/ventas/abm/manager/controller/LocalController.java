package com.ventas.abm.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.abm.manager.domain.Local;
import com.ventas.abm.manager.service.LocalService;

@RestController
@RequestMapping("/local")
public class LocalController {

	@Autowired
	LocalService localService;

	@PostMapping()
	public ResponseEntity<Local> crearLocal(@RequestBody Local local) {
		// TODO: process POST request
		Local nuevoLocal = localService.createLocal(local);
		return ResponseEntity.ok(nuevoLocal);
	}

	@PutMapping()
	public ResponseEntity<Local> modificarLocal(@RequestBody Local local) {
		// TODO: process PUT request
		Local modificarLocal = localService.modifyLocal(local);
		return ResponseEntity.ok(modificarLocal);
	}

}

package com.ventas.abm.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import com.ventas.abm.manager.domain.Vendedor;
import com.ventas.abm.manager.models.VendedorDto;
import com.ventas.abm.manager.service.VendedorService;

@RestController
@RequestMapping("vendedor")
public class VendedorController {

	@Autowired
	VendedorService service;

	@PostMapping
	public ResponseEntity<Vendedor> crearVendedor(@RequestBody Vendedor vendedor) {
		//TODO: process POST request
		try {
			Vendedor nuevaTienda = service.createVendedor(vendedor);
			return ResponseEntity.ok(nuevaTienda);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<Vendedor> editarVendedor(@RequestBody Vendedor vendedor) {
		//TODO: process PUT request
		try {
			Vendedor modificarVendedor = service.modifyVendedor(vendedor);
			return ResponseEntity.ok(modificarVendedor);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Vendedor> bajaMVendedor(@PathVariable("id") Long idVendedor) {
		try {
			Vendedor vendedor = service.deleteVendedor(idVendedor);
			return ResponseEntity.ok(vendedor);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Vendedor> obtenerVendedorPorId(@PathVariable("id") Long idVendedor) {
		try {
			Vendedor vendedor = service.getVendedorById(idVendedor);
			return ResponseEntity.ok(vendedor);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/nombre/{nombre}")
	private ResponseEntity<List<VendedorDto>> obtenerVendedorPorNombre(@PathVariable String nombre) throws Exception {
		try {
			List<VendedorDto> vendedores = service.getVendedorByNombre(nombre);
			return ResponseEntity.ok(vendedores);
		} catch (NotFoundException e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/apellido/{apellido}")
	private ResponseEntity<List<VendedorDto>> obtenerVendedoPorApellido(@PathVariable String apellido)
			throws Exception {
		try {
			List<VendedorDto> vendedores = service.getVendedorByApellido(apellido);
			return ResponseEntity.ok(vendedores);
		} catch (NotFoundException e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@GetMapping("/codigo/{codigo}")
	private ResponseEntity<VendedorDto> obtenerPorCodigo(@PathVariable Integer codigo) throws Exception {
		try {
			VendedorDto vendedor = service.getVendedorByCodigo(codigo);
			return ResponseEntity.ok(vendedor);
		} catch (NotFoundException e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}

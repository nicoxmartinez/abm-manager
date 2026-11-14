package com.ventas.abm.manager.service;

import java.util.List;

import com.ventas.abm.manager.domain.Vendedor;
import com.ventas.abm.manager.model.VendedorDto;

public interface VendedorService {

	Vendedor createVendedor(Vendedor vendedor) throws Exception;

	Vendedor modifyVendedor(Vendedor vendedor) throws Exception;

	Vendedor deleteVendedor(long idVendedor);

	Vendedor getVendedorById(Long idVendedor);

	List<VendedorDto> getVendedorByNombre(String nombre) throws Exception;

	List<VendedorDto> getVendedorByApellido(String apellido) throws Exception;

	VendedorDto getVendedorByCodigo(Integer codigo) throws Exception;

}
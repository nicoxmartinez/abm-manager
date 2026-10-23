package com.ventas.abm.manager.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ventas.abm.manager.domain.Vendedor;
import com.ventas.abm.manager.models.VendedorDto;

@Component
public class VendedorMapper {

	public List<VendedorDto> mapearListaVendedorDto(List<Vendedor> listaVendedores) {
		// TODO Auto-generated method stub
		List<VendedorDto> listaDto = new ArrayList<>();

		for (Vendedor vendedor : listaVendedores) {
			VendedorDto vendedorDto = mapearVendedorDto(vendedor);
			listaDto.add(vendedorDto);
		}

		return listaDto;
	}

	public VendedorDto mapearVendedorDto(Vendedor vendedor) {
		// TODO Auto-generated method stub
		VendedorDto dto = new VendedorDto();

		if (vendedor.getIdVendedor() != null) {
			dto.setIdVendedor(vendedor.getIdVendedor());
		}
		dto.setNombre(vendedor.getNombre());
		dto.setApellido(vendedor.getApellido());
		dto.setCodigo(vendedor.getCodigo());
		dto.setDocumento(vendedor.getDocumento());
		dto.setUsuario(vendedor.getUsuario());
		dto.setFechaAlta(vendedor.getFechaAlta());
		dto.setFechaBaja(vendedor.getFechaBaja());
		dto.setFechaModificacion(vendedor.getFechaModificacion());

		return dto;
	}

}

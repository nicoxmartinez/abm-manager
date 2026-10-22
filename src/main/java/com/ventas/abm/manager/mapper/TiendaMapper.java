package com.ventas.abm.manager.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ventas.abm.manager.domain.Tienda;
import com.ventas.abm.manager.models.TiendaDto;

@Component
public class TiendaMapper {

	public List<TiendaDto> mapearListaTiendaDto(List<Tienda> listaTiendas) {
		// TODO Auto-generated method stub
		List<TiendaDto> listaDto = new ArrayList<>();
		for (Tienda tienda : listaTiendas) {
			TiendaDto dto = mapearTiendaDto(tienda);
			listaDto.add(dto);
		}
		return listaDto;
	}

	public TiendaDto mapearTiendaDto(Tienda tienda) {
		// TODO Auto-generated method stub
		TiendaDto dto = new TiendaDto();
		if (tienda.getIdTienda() != null) {
			dto.setIdTienda(tienda.getIdTienda());
		}
		dto.setCodigo(tienda.getCodigo());
		dto.setDescripcion(tienda.getDescripcion());
		dto.setTipoTienda(tienda.getTipo().getDescripcion());
		return dto;
	}

}

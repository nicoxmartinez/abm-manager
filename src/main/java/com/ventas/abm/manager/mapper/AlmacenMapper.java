package com.ventas.abm.manager.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ventas.abm.manager.domain.Almacen;
import com.ventas.abm.manager.models.AlmacenDto;
import com.ventas.abm.manager.models.TiendaDto;

@Component
public class AlmacenMapper {

	@Autowired
	TiendaMapper tiendaMapper;

	public List<AlmacenDto> mapearListaAlmacenDto(List<Almacen> listaAlmacenes) {
		// TODO Auto-generated method stub
		List<AlmacenDto> listaDto = new ArrayList<>();
		for (Almacen almacen : listaAlmacenes) {
			AlmacenDto dto = mapearAlmacenDto(almacen);
			listaDto.add(dto);
		}
		return listaDto;
	}

	public AlmacenDto mapearAlmacenDto(Almacen almacen) {
		// TODO Auto-generated method stub
		AlmacenDto dto = new AlmacenDto();
		dto.setIdAlmacen(almacen.getIdAlmacen());
		dto.setCodigo(almacen.getCodigo());
		dto.setDescripcion(almacen.getDescripcion());
		if (almacen.getTienda() != null) {
			TiendaDto tiendaDto = tiendaMapper.mapearTiendaDto(almacen.getTienda());
			dto.setTienda(tiendaDto);
		}
		dto.setFechaBaja(almacen.getFechaBaja());
		if (almacen.getDomicilio() != null) {
			String direccion = almacen.getDomicilio().getCalle() + " " + almacen.getDomicilio().getNumero() + " "
					+ almacen.getDomicilio().getPartido() + " " + almacen.getDomicilio().getProvincia() + " "
					+ almacen.getDomicilio().getPais();
			dto.setDireccion(direccion);
		}
		return dto;
	}

}

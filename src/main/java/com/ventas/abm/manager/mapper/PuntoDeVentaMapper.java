package com.ventas.abm.manager.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ventas.abm.manager.domain.PuntoDeVenta;
import com.ventas.abm.manager.models.PuntoDeVentaDto;

@Component
public class PuntoDeVentaMapper {

	public List<PuntoDeVentaDto> mapearListaPuntoDeVentaDto(List<PuntoDeVenta> listaPuntosDeVenta) {
		// TODO Auto-generated method stub
		List<PuntoDeVentaDto> listaDto = new ArrayList<>();
		for (PuntoDeVenta puntoDeVenta : listaPuntosDeVenta) {
			PuntoDeVentaDto dto = mapearPuntoDeVentaDto(puntoDeVenta);
			listaDto.add(dto);
		}
		return listaDto;
	}

	public PuntoDeVentaDto mapearPuntoDeVentaDto(PuntoDeVenta puntoDeVenta) {
		// TODO Auto-generated method stub
		PuntoDeVentaDto dto = new PuntoDeVentaDto();
		dto.setIdPuntoDeVenta(puntoDeVenta.getIdPuntoDeVenta());
		dto.setNombreTerminal(puntoDeVenta.getNombreTerminal());
		dto.setNumeroTerminal(puntoDeVenta.getNumeroTerminal());
		dto.setNumeroDeCaja(puntoDeVenta.getNumeroDeCaja());
		return dto;
	}

}

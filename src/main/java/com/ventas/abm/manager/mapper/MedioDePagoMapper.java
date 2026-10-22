package com.ventas.abm.manager.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ventas.abm.manager.domain.MedioDePago;
import com.ventas.abm.manager.models.FinanciacionDto;
import com.ventas.abm.manager.models.MedioDePagoDto;

@Component
public class MedioDePagoMapper {

	@Autowired
	FinanciacionMapper financiacionMapper;

	public List<MedioDePagoDto> mapearListaMedioDePagoDto(List<MedioDePago> listaMediosDePago) {
		// TODO Auto-generated method stub
		List<MedioDePagoDto> listaDto = new ArrayList<>();
		for (MedioDePago medioDePago : listaMediosDePago) {
			MedioDePagoDto dto = mapearMedioDePagoDto(medioDePago);
			listaDto.add(dto);
		}
		return listaDto;
	}

	public MedioDePagoDto mapearMedioDePagoDto(MedioDePago medioDePago) {
		// TODO Auto-generated method stub
		MedioDePagoDto dto = new MedioDePagoDto();
		dto.setIdMedioPago(medioDePago.getIdMedioPago());
		dto.setCodigo(medioDePago.getCodigo());
		dto.setDetalle(medioDePago.getDetalle());
		dto.setTipo(medioDePago.getTipo().getDescripcion());
		dto.setHabilitado(medioDePago.getHabilitado());
		if (medioDePago.getFinanciacion() != null) {
			FinanciacionDto financiacionDto = financiacionMapper.mapearFinanciacionDto(medioDePago.getFinanciacion());
			dto.setFinanciacion(financiacionDto);
		}
		dto.setFechaBaja(medioDePago.getFechaBaja());
		return dto;
	}

}

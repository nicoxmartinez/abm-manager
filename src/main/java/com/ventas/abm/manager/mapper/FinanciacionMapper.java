package com.ventas.abm.manager.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ventas.abm.manager.domain.Financiacion;
import com.ventas.abm.manager.domain.PlanCuota;
import com.ventas.abm.manager.models.FinanciacionDto;

@Component
public class FinanciacionMapper {

	public List<FinanciacionDto> mapearListaFinanciacionDto(List<Financiacion> listaFinanciacion) {
		// TODO Auto-generated method stub
		List<FinanciacionDto> listaDto = new ArrayList<>();
		for (Financiacion financiacion : listaFinanciacion) {
			FinanciacionDto dto = mapearFinanciacionDto(financiacion);
			listaDto.add(dto);
		}
		return listaDto;
	}

	public FinanciacionDto mapearFinanciacionDto(Financiacion financiacion) {
		// TODO Auto-generated method stub
		FinanciacionDto dto = new FinanciacionDto();
		dto.setIdFinanciacion(financiacion.getIdFinanciacion());
		dto.setCodigo(financiacion.getCodigo());
		dto.setDescripcion(financiacion.getDescripcion());
		dto.setActiva(financiacion.getActiva());
		if (financiacion.getPlanCuota() != null) {
			List<Integer> cuotas = new ArrayList<>();
			for (PlanCuota planCuota : financiacion.getPlanCuota()) {
				cuotas.add(planCuota.getCantidadCuotas());
			}
			dto.setPlanCuotas(cuotas);
		}
		return dto;
	}

}

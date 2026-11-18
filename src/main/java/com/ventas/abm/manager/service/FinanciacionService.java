package com.ventas.abm.manager.service;

import java.util.List;

import com.ventas.abm.manager.domain.Financiacion;
import com.ventas.abm.manager.model.FinanciacionDto;

public interface FinanciacionService {

	Financiacion createFinanciacion(Financiacion financiacion);

	Financiacion modifyFinanciacion(Financiacion financiacion);

	Financiacion deleteFinanciacion(Long idFinanciacion);

	List<FinanciacionDto> getAllFinanciacionActiva();

	Financiacion getFinanciacionById(Long idFinanciacion);

	FinanciacionDto getFinanciacionByCodigo(Integer codigo);

	List<FinanciacionDto> getFinanciacionByDescripcion(String descripcion);

}

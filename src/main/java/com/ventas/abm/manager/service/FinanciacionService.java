package com.ventas.abm.manager.service;

import java.util.List;

import com.ventas.abm.manager.domain.Financiacion;
import com.ventas.abm.manager.model.FinanciacionDto;

public interface FinanciacionService {

	Financiacion createFinanciacion(Financiacion financiacion) throws Exception;

	Financiacion modifyFinanciacion(Financiacion financiacion) throws Exception;

	Financiacion deleteFinanciacion(Long idFinanciacion) throws Exception;

	List<FinanciacionDto> getAllFinanciacionActiva() throws Exception;

	Financiacion getFinanciacionById(Long idFinanciacion);

	FinanciacionDto getFinanciacionByCodigo(Integer codigo) throws Exception;

	List<FinanciacionDto> getFinanciacionByDescripcion(String descripcion) throws Exception;

}

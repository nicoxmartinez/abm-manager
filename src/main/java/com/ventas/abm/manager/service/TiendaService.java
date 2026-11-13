package com.ventas.abm.manager.service;

import java.util.List;

import com.ventas.abm.manager.domain.Tienda;
import com.ventas.abm.manager.models.TiendaDto;

public interface TiendaService {

	Tienda deleteTienda(Long idTienda) throws Exception;

	Tienda getTiendaById(Long idTienda);

	TiendaDto getTiendaByCodigo(String codigo) throws Exception;

	List<TiendaDto> getTiendaByDescripcion(String descripcion) throws Exception;

}

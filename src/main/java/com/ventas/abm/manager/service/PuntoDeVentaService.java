package com.ventas.abm.manager.service;

import java.util.List;

import com.ventas.abm.manager.domain.PuntoDeVenta;
import com.ventas.abm.manager.model.PuntoDeVentaDto;

public interface PuntoDeVentaService {

	PuntoDeVenta createPuntoDeVenta(PuntoDeVenta puntoDeVenta) throws Exception;

	PuntoDeVenta modifyPuntoDeVenta(PuntoDeVenta puntoDeVenta) throws Exception;

	PuntoDeVenta getPuntoDeVentaById(Long idPuntoDeVenta);

	PuntoDeVentaDto getPuntoDeVentaByNumeroTerminal(Integer numeroTerminal) throws Exception;

	List<PuntoDeVentaDto> getPuntoDeVentaByNombreTerminal(String nombreTerminal) throws Exception;
}

package com.ventas.abm.manager.service;

import java.util.List;

import com.ventas.abm.manager.domain.MedioDePago;
import com.ventas.abm.manager.models.MedioDePagoDto;

public interface MedioDePagoService {

	MedioDePago createMedioDePago(MedioDePago medioDePago) throws Exception;

	MedioDePago modifyMedioDePago(MedioDePago medioDePago) throws Exception;

	MedioDePago deleteMedioDePago(Long idMedioPago) throws Exception;

	MedioDePago getMedioDePagoById(Long idMedioPago) throws Exception;

	MedioDePagoDto getMedioDePagoByCodigo(Integer codigo) throws Exception;

	List<MedioDePagoDto> getMedioDePagoByDetalle(String detalle) throws Exception;

}

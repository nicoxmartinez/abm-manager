package com.ventas.abm.manager.service;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.PuntoDeVenta;
import com.ventas.abm.manager.repository.PuntoDeVentaRepository;

@Service
public class PuntoDeVentaService {

	@Autowired
	PuntoDeVentaRepository puntoDeVentaRepository;

	private static final Log logger = LogFactory.getLog(PuntoDeVentaService.class);

	public PuntoDeVenta createPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		// TODO Auto-generated method stub
		return null;
	}

	public PuntoDeVenta modifyPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
		// TODO Auto-generated method stub
		return null;
	}

	public PuntoDeVenta getPuntoDeVentaById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public PuntoDeVenta getPuntoDeVentaByNumero(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

	public PuntoDeVenta getPuntoDeVentaByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

}

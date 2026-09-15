package com.ventas.abm.manager.service;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.MedioDePago;
import com.ventas.abm.manager.domain.Tienda;
import com.ventas.abm.manager.repository.FinanciacionRepository;

@Service
public class TiendaService {

	@Autowired
	FinanciacionRepository financiacionRepository;

	private static final Log logger = LogFactory.getLog(TiendaService.class);

	public Tienda createTienda(Tienda tienda) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tienda modifyTienda(Tienda tienda) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tienda deleteTienda(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tienda getTiendaById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tienda getTiendaByCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tienda> getTiendaByDescripcion(String descripcion) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.ventas.abm.manager.service;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.Financiacion;
import com.ventas.abm.manager.repository.FinanciacionRepository;

@Service
public class FinanciacionService {

	@Autowired
	FinanciacionRepository financiacionRepository;

	private static final Log logger = LogFactory.getLog(AlmacenService.class);

	public Financiacion createFinanciacion(Financiacion financiacion) {
		// TODO Auto-generated method stub
		return null;
	}

	public Financiacion modifyFinanciacion(Financiacion financiacion) {
		// TODO Auto-generated method stub
		return null;
	}

	public Financiacion deleteFinanciacion(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Financiacion> getAllFinanciacion() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Financiacion> getAllFinanciacionActiva() {
		// TODO Auto-generated method stub
		return null;
	}

	public Financiacion getFinanciacionById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Financiacion getFinanciacionByCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Financiacion> getFinanciacionByDescripcion(String descripcion) {
		// TODO Auto-generated method stub
		return null;
	}

}

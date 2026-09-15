package com.ventas.abm.manager.service;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.Almacen;
import com.ventas.abm.manager.repository.AlmacenRepository;

@Service
public class AlmacenService {
	
	@Autowired
	AlmacenRepository almacenRepository;

	private static final Log logger = LogFactory.getLog(AlmacenService.class);

	public Almacen createAlmacen(Almacen almacen) {
		// TODO Auto-generated method stub
		return null;
	}

	public Almacen modifyAlmacen(Almacen almacen) {
		// TODO Auto-generated method stub
		return null;
	}

	public Almacen deleteAlmacen(long idAlmacen) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Almacen> getAllAlmacen() {
		// TODO Auto-generated method stub
		return null;
	}

	public Almacen getAlmacenById(Long idAlmacen) {
		// TODO Auto-generated method stub
		return null;
	}

	public Almacen getAlmacenByCodigo(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Almacen> getAlmacenByDescripcion(String descripcion) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.ventas.abm.manager.service;

import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.MedioDePago;
import com.ventas.abm.manager.repository.MedioDePagoRepository;

@Service
public class MedioDePagoService {

	@Autowired
	MedioDePagoRepository medioDePagoRepository;

	private static final Log logger = LogFactory.getLog(MedioDePagoService.class);

	public MedioDePago createMedioDePago(MedioDePago medioDePago) {
		// TODO Auto-generated method stub
		return null;
	}

	public MedioDePago modifyMedioDePago(MedioDePago medioDePago) {
		// TODO Auto-generated method stub
		return null;
	}

	public MedioDePago deleteMedioDePago(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MedioDePago> getAllMedioDePagoHabilitados() {
		// TODO Auto-generated method stub
		return null;
	}

	public MedioDePago getMedioDePagoById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public MedioDePago getMedioDePagoByCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<MedioDePago> getMedioDePagoByDetalle(String detalle) {
		// TODO Auto-generated method stub
		return null;
	}

}

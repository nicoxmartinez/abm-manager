package com.ventas.abm.manager.service;

import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.Domicilio;
import com.ventas.abm.manager.repository.DomicilioRepository;

@Service
public class DomicilioService {

	@Autowired
	DomicilioRepository domicilioRepository;

	private static final Log logger = LogFactory.getLog(DomicilioService.class);

	public Domicilio createDomicilio(Domicilio domicilio) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Domicilio";
		logger.info(logMensaje);
		Domicilio domicilioNuevo = new Domicilio();
		try {
			domicilioNuevo.setCalle(domicilio.getCalle());
			domicilioNuevo.setNumero(domicilio.getNumero());
			domicilioNuevo.setEntreCalles(domicilio.getEntreCalles());
			domicilioNuevo.setLocalidad(domicilio.getLocalidad());
			domicilioNuevo.setPartido(domicilio.getPartido());
			domicilioNuevo.setCodigoPostal(domicilio.getCodigoPostal());
			domicilioNuevo.setProvincia(domicilio.getProvincia());
			domicilioNuevo.setObservaciones(domicilio.getObservaciones());
			domicilioRepository.save(domicilioNuevo);
			logMensaje = "Registrada correctamente Domicilio: " + domicilioNuevo.getCalle() + " "
					+ domicilioNuevo.getLocalidad() + "" + domicilioNuevo.getPartido() + " "
					+ domicilioNuevo.getProvincia();
			logger.info(logMensaje);
		} catch (Exception e) {
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		return domicilioNuevo;
	}

	public Domicilio modifyDomicilio(Domicilio domicilio) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro Domicilio ID: " + domicilio.getIdDomicilio();
		logger.info(logMensaje);
		Optional<Domicilio> optional = domicilioRepository.findById(domicilio.getIdDomicilio());
		if (optional.isPresent()) {
			logMensaje = "No existe un Domicilio con ID: " + domicilio.getIdDomicilio();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		Domicilio domicilioEnBd = optional.get();
		try {
			domicilioEnBd.setCalle(domicilio.getCalle());
			domicilioEnBd.setNumero(domicilio.getNumero());
			domicilioEnBd.setEntreCalles(domicilio.getEntreCalles());
			domicilioEnBd.setLocalidad(domicilio.getLocalidad());
			domicilioEnBd.setPartido(domicilio.getPartido());
			domicilioEnBd.setCodigoPostal(domicilio.getCodigoPostal());
			domicilioEnBd.setProvincia(domicilio.getProvincia());
			domicilioEnBd.setObservaciones(domicilio.getObservaciones());
			Domicilio domicilioModificado = domicilioRepository.save(domicilioEnBd);
			logMensaje = "Modificado correctamente Domicilio ID: " + domicilioModificado.getIdDomicilio();
			logger.info(logMensaje);
			return domicilioModificado;
		} catch (Exception e) {
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public Domicilio getDomicilioById(Long idDomicilio) {
		// TODO Auto-generated method stub
		Domicilio domicilio = new Domicilio();
		try {
			Optional<Domicilio> domicilioOptional = domicilioRepository.findById(idDomicilio);
			if (domicilioOptional.isPresent()) {
				domicilio = domicilioOptional.get();
			}
			return domicilio;
		} catch (Exception e) {
			logger.error("No se encontró Domicilio con el ID: " + idDomicilio);
			return null;
		}
	}

	public List<Domicilio> getDomicilioByDireccion(String direccion) throws Exception {
		// TODO Auto-generated method stub
		List<Domicilio> listaDomicilios = domicilioRepository.findByDireccionLike(direccion);
		if (listaDomicilios.isEmpty()) {
			String logMensaje = "No se encontró Dirección: " + direccion;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		return listaDomicilios;
	}

}

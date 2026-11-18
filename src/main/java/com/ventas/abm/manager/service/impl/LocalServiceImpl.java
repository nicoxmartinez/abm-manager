package com.ventas.abm.manager.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.Domicilio;
import com.ventas.abm.manager.domain.Local;
import com.ventas.abm.manager.exception.EntidadNoEncontradaException;
import com.ventas.abm.manager.exception.ErrorIntegridadException;
import com.ventas.abm.manager.exception.ErrorValorNuloException;
import com.ventas.abm.manager.exception.ExisteEntidadCodigoException;
import com.ventas.abm.manager.repository.LocalRepository;
import com.ventas.abm.manager.service.DomicilioService;
import com.ventas.abm.manager.service.LocalService;

import jakarta.transaction.Transactional;

@Service
public class LocalServiceImpl implements LocalService {

	@Autowired
	LocalRepository localRepository;

	@Autowired
	DomicilioService domicilioService;

	private static final Log logger = LogFactory.getLog(LocalServiceImpl.class);

	@Override
	@Transactional
	public Local createLocal(Local local) {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Local: " + local.getCodigo() + " " + local.getDescripcion();
		logger.info(logMensaje);
		if (local.getIdTienda() != null) {
			logMensaje = "La creacion de Local no puede tener ID: " + local.getIdTienda();
			throw new ErrorIntegridadException(logMensaje);
		}
		if (local.getCodigo() != null) {
			Optional<Local> verificarCodigoLocal = localRepository.findByCodigo(local.getCodigo());
			if (verificarCodigoLocal.isPresent()) {
				logMensaje = "Existe un registro de Local con el Código: " + local.getCodigo();
				logger.error(logMensaje);
				throw new ExisteEntidadCodigoException(logMensaje);
			}
		} else {
			logMensaje = "El código de Tienda es requerido";
			logger.error(logMensaje);
			throw new ErrorValorNuloException(logMensaje);
		}
		Local nuevoLocal = new Local();
		try {
			// Datos de Tienda
			nuevoLocal.setCodigo(local.getCodigo());
			nuevoLocal.setDescripcion(local.getDescripcion());
			nuevoLocal.setUsuario(local.getUsuario());
			nuevoLocal.setEmail(local.getEmail());
			nuevoLocal.setTelefono(local.getTelefono());
			nuevoLocal.setFechaAlta(new Date());
			nuevoLocal.setFechaBaja(null);
			// Datos de Local
			nuevoLocal.setHorarioApertura(local.getHorarioApertura());
			nuevoLocal.setHorarioCierre(local.getHorarioCierre());
			// Domicilio
			if (local.getDomicilio() != null) {
				Domicilio domicilio = local.getDomicilio();
				Domicilio domicilioLocal = domicilioService.createDomicilio(domicilio);
				if (domicilioLocal != null) {
					nuevoLocal.setDomicilio(domicilioLocal);
				}
			}
			localRepository.save(nuevoLocal);
			logMensaje = "Registrado correctamente Local ID: " + nuevoLocal.getIdTienda();
			logger.info(logMensaje);
		} catch (RuntimeException e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new RuntimeException(logMensaje);
		}
		return nuevoLocal;
	}

	@Override
	@Transactional
	public Local modifyLocal(Local local) {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro de Local ID: " + local.getIdTienda();
		logger.info(logMensaje);
		if (local.getIdTienda() != null) {
			Optional<Local> localOptional = localRepository.findById(local.getIdTienda());
			if (localOptional.isPresent()) {
				Local localEnBd = localOptional.get();
				// Datos de Tienda
				localEnBd.setCodigo(local.getCodigo());
				localEnBd.setDescripcion(local.getDescripcion());
				localEnBd.setUsuario(local.getUsuario());
				localEnBd.setEmail(local.getEmail());
				localEnBd.setTelefono(local.getTelefono());
				localEnBd.setFechaAlta(local.getFechaAlta());
				localEnBd.setFechaBaja(local.getFechaBaja());
				// Datos de Domicilio
				if (local.getDomicilio() != null) {
					Domicilio domicilioLocal = local.getDomicilio();
					if (domicilioLocal.getIdDomicilio() != null) {
						Domicilio domicilioModificado = domicilioService.modifyDomicilio(domicilioLocal);
						localEnBd.setDomicilio(domicilioModificado);
					} else {
						Domicilio nuevoDomicilio = domicilioService.createDomicilio(domicilioLocal);
						localEnBd.setDomicilio(nuevoDomicilio);
					}
				} else if (localEnBd.getDomicilio() != null) {
					domicilioService.deleteDomicilio(localEnBd.getDomicilio().getIdDomicilio());
					localEnBd.setDomicilio(null);
				}
				Local localModificado = localRepository.save(localEnBd);
				logMensaje = "Modificado correctamente Local: " + localModificado.getCodigo() + " "
						+ localModificado.getDescripcion();
				logger.info(logMensaje);
				return localModificado;
			} else {
				logMensaje = "No existe un Local con ID: " + local.getIdTienda();
				logger.error(logMensaje);
				throw new EntidadNoEncontradaException(logMensaje);
			}
		} else {
			logMensaje = "El Local ID es requerido: " + local.getIdTienda();
			logger.error(logMensaje);
			throw new ErrorValorNuloException(logMensaje);
		}
	}

	@Override
	public Local getLocalById(Long idTienda) {
		// TODO Auto-generated method stub
		Local local = new Local();
		Optional<Local> localOptional = localRepository.findById(idTienda);
		if (localOptional.isPresent()) {
			local = localOptional.get();
			return local;
		} else {
			String logMensaje = "No se encontraron Local con el ID: " + idTienda;
			logger.error(logMensaje);
			return null;
		}
	}

}

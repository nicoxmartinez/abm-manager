package com.ventas.abm.manager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.Domicilio;
import com.ventas.abm.manager.domain.Tienda;
import com.ventas.abm.manager.repository.TiendaRepository;

import jakarta.transaction.Transactional;

@Service
public class TiendaService {

	@Autowired
	TiendaRepository tiendaRepository;

	@Autowired
	DomicilioService domicilioService;

	private static final Log logger = LogFactory.getLog(TiendaService.class);

	@Transactional
	public Tienda createTienda(Tienda tienda) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Tienda";
		logger.info(logMensaje);
		Optional<Tienda> verificarTiendaCodigo = tiendaRepository.findByCodigo(tienda.getCodigo());
		if (verificarTiendaCodigo.isPresent()) {
			logMensaje = "Existe un registro de Tienda con el Código " + tienda.getCodigo();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		Tienda tiendaNueva = new Tienda();
		try {
			tiendaNueva.setCodigo(tienda.getCodigo());
			tiendaNueva.setDescripcion(tienda.getDescripcion());
			tiendaNueva.setMailContacto(tienda.getMailContacto());
			tiendaNueva.setTelefono(tienda.getTelefono());
			tiendaNueva.setFechaBaja(null);
			if (tienda.getDomicilio() != null) {
				Domicilio domicilio = tienda.getDomicilio();
				Domicilio domicilioTienda = domicilioService.createDomicilio(domicilio);
				if (domicilioTienda != null) {
					tiendaNueva.setDomicilio(domicilioTienda);
				}
			}
			tiendaRepository.save(tiendaNueva);
			logMensaje = "Registrada correctamente Tienda: " + tiendaNueva.getCodigo() + " "
					+ tiendaNueva.getDescripcion();
			logger.info(logMensaje);
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			throw new Exception(logMensaje);

		}
		return tiendaNueva;
	}

	@Transactional
	public Tienda modifyTienda(Tienda tienda) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro Tienda ID: " + tienda.getIdTienda();
		logger.info(logMensaje);
		Optional<Tienda> tiendaOptional = tiendaRepository.findById(tienda.getIdTienda());
		if (tiendaOptional.isPresent()) {
			Tienda tiendaEnBd = tiendaOptional.get();
			tiendaEnBd.setCodigo(tienda.getCodigo());
			tiendaEnBd.setDescripcion(tienda.getDescripcion());
			tiendaEnBd.setMailContacto(tienda.getMailContacto());
			tiendaEnBd.setTelefono(tienda.getTelefono());
			tiendaEnBd.setFechaBaja(tienda.getFechaBaja());
			if (tienda.getDomicilio() != null) {
				Domicilio domicilioTienda = tienda.getDomicilio();

				if (domicilioTienda.getIdDomicilio() != null) {
					Domicilio domicilioModificado = domicilioService.modifyDomicilio(domicilioTienda);
					tiendaEnBd.setDomicilio(domicilioModificado);
				} else {
					if (tiendaEnBd.getDomicilio() != null && tiendaEnBd.getDomicilio().getIdDomicilio() != null) {
						domicilioService.deleteDomicilio(tiendaEnBd.getDomicilio().getIdDomicilio());
					}
					Domicilio nuevoDomicilio = domicilioService.createDomicilio(domicilioTienda);
					tiendaEnBd.setDomicilio(nuevoDomicilio);
				}
			} else {
				if (tiendaEnBd.getDomicilio() != null && tiendaEnBd.getDomicilio().getIdDomicilio() != null) {
					domicilioService.deleteDomicilio(tiendaEnBd.getDomicilio().getIdDomicilio());
				}
				tiendaEnBd.setDomicilio(null);
			}
			Tienda tiendaModificada = tiendaRepository.save(tiendaEnBd);
			logMensaje = "Modificado correctamente Tienda ID: " + tiendaModificada.getIdTienda();
			logger.info(logMensaje);
			return tiendaModificada;
		} else {
			logMensaje = "No existe un Tienda con ID: " + tienda.getIdTienda();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public Tienda deleteTienda(Long idTienda) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Realizando baja de Tienda ID: " + idTienda;
		logger.info(logMensaje);
		Optional<Tienda> tienda = tiendaRepository.findById(idTienda);
		if (tienda.isPresent()) {
			Tienda bajaDeTienda = tienda.get();
			bajaDeTienda.setFechaBaja(new Date());
			tiendaRepository.save(bajaDeTienda);
			logMensaje = "Se dio de baja correctamente Tienda: " + bajaDeTienda.getCodigo() + " "
					+ bajaDeTienda.getDescripcion();
			logger.info(logMensaje);
			return bajaDeTienda;
		} else {
			logMensaje = "No existe un Tienda con ID: " + idTienda;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public Tienda getTiendaById(Long idTienda) {
		// TODO Auto-generated method stub
		Tienda tienda = new Tienda();
		Optional<Tienda> tiendaOptional = tiendaRepository.findById(idTienda);
		if (tiendaOptional.isPresent()) {
			tienda = tiendaOptional.get();
			return tienda;
		} else {
			logger.error("No se encontró Tienda con el ID: " + idTienda);
			return null;
		}
	}

	public Tienda getTiendaByCodigo(String codigo) throws Exception {
		// TODO Auto-generated method stub
		Optional<Tienda> tiendaOptional = tiendaRepository.findByCodigo(codigo);
		if (tiendaOptional.isPresent()) {
			Tienda tienda = tiendaOptional.get();
			return tienda;
		} else {
			String logMensaje = "No se encontró Tienda con el código: " + codigo;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public List<Tienda> getTiendaByDescripcion(String descripcion) throws Exception {
		// TODO Auto-generated method stub
		List<Tienda> listaTienda = tiendaRepository.findByDescripcionLike(descripcion);
		if (!listaTienda.isEmpty()) {
			return listaTienda;
		} else {
			String logMensaje = "No se encontraron Tiendas con la descripción: " + descripcion;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

}

package com.ventas.abm.manager.service;

import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.PuntoDeVenta;
import com.ventas.abm.manager.domain.Tienda;
import com.ventas.abm.manager.repository.PuntoDeVentaRepository;

@Service
public class PuntoDeVentaService {

	@Autowired
	PuntoDeVentaRepository puntoDeVentaRepository;

	@Autowired
	TiendaService tiendaService;

	private static final Log logger = LogFactory.getLog(PuntoDeVentaService.class);

	public PuntoDeVenta createPuntoDeVenta(PuntoDeVenta puntoDeVenta) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Punto de Venta";
		logger.info(logMensaje);
		PuntoDeVenta puntoDeVentaNuevo = new PuntoDeVenta();
		if (puntoDeVenta.getTienda() != null) {
			Tienda tienda = tiendaService.getTiendaById(puntoDeVenta.getTienda().getIdTienda());
			if (tienda != null) {
				puntoDeVentaNuevo.setTienda(tienda);
			} else {
				logMensaje = "No existe un registro de Tienda " + puntoDeVenta.getTienda().getCodigo() + " "
						+ puntoDeVenta.getTienda().getDescripcion();
				logger.error(logMensaje);
				throw new Exception(logMensaje);
			}
		} else {
			logMensaje = "Seleccionar una Tienda es obligatorio";
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		if (puntoDeVenta.getNumero() != null) {

			Optional<PuntoDeVenta> verificarNumeroDePunto = puntoDeVentaRepository
					.findByNumero(puntoDeVenta.getNumero());
			if (verificarNumeroDePunto.isPresent()) {
				logMensaje = "Existe un Punto de Venta con el Número " + puntoDeVenta.getNumero();
				logger.error(logMensaje);
				throw new Exception(logMensaje);
			}
		} else {
			logMensaje = "El Número del Punto de Venta es obligatorio";
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		try {
			puntoDeVentaNuevo.setNombre(puntoDeVenta.getNombre());
			puntoDeVentaNuevo.setNumero(puntoDeVenta.getNumero());
			puntoDeVentaNuevo.setUsuario(puntoDeVenta.getUsuario());
			puntoDeVentaNuevo.setNumeroDeCaja(puntoDeVenta.getNumeroDeCaja());
			puntoDeVentaRepository.save(puntoDeVentaNuevo);
			logMensaje = "Registrada correctamente Tienda: " + puntoDeVentaNuevo.getNumero() + " "
					+ puntoDeVentaNuevo.getNombre();
			logger.info(logMensaje);
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			throw new Exception(logMensaje);

		}
		return puntoDeVentaNuevo;
	}

	public PuntoDeVenta modifyPuntoDeVenta(PuntoDeVenta puntoDeVenta) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro Tienda ID: " + puntoDeVenta.getId();
		logger.info(logMensaje);
		Optional<PuntoDeVenta> puntoDeVentaOptional = puntoDeVentaRepository.findById(puntoDeVenta.getId());
		if (puntoDeVentaOptional.isPresent()) {
			PuntoDeVenta puntoDeVentaEnBd = puntoDeVentaOptional.get();
			if (puntoDeVenta.getTienda() != null) {
				Tienda tienda = tiendaService.getTiendaById(puntoDeVenta.getTienda().getIdTienda());
				if (tienda != null) {
					puntoDeVentaEnBd.setTienda(tienda);
				}
			}
			puntoDeVentaEnBd.setNumero(puntoDeVenta.getNumero());
			puntoDeVentaEnBd.setNombre(puntoDeVenta.getNombre());
			puntoDeVentaEnBd.setUsuario(puntoDeVenta.getUsuario());
			puntoDeVentaEnBd.setNumeroDeCaja(puntoDeVenta.getNumeroDeCaja());
			PuntoDeVenta puntoDeVentaModificado = puntoDeVentaRepository.save(puntoDeVentaEnBd);
			logMensaje = "Modificado correctamente Tienda ID: " + puntoDeVentaModificado.getId();
			logger.info(logMensaje);
			return puntoDeVentaModificado;
		} else {
			logMensaje = "No existe un Punto de Venta con ID: " + puntoDeVenta.getId();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public PuntoDeVenta getPuntoDeVentaById(Long id) {
		// TODO Auto-generated method stub
		Optional<PuntoDeVenta> puntoDeVentaOptional = puntoDeVentaRepository.findById(id);
		if (puntoDeVentaOptional.isPresent()) {
			PuntoDeVenta puntoDeVenta = puntoDeVentaOptional.get();
			return puntoDeVenta;
		} else {
			logger.error("No se encontró Punto de Venta con el ID: " + id);
			return null;
		}
	}

	public PuntoDeVenta getPuntoDeVentaByNumero(Integer numero) throws Exception {
		// TODO Auto-generated method stub
		Optional<PuntoDeVenta> puntoDeVentaOptional = puntoDeVentaRepository.findByNumero(numero);
		if (puntoDeVentaOptional.isPresent()) {
			PuntoDeVenta puntoDeVenta = puntoDeVentaOptional.get();
			return puntoDeVenta;
		} else {
			String logMensaje = "No se encontró Punto de Venta con el Número: " + numero;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public List<PuntoDeVenta> getPuntoDeVentaByNombre(String nombre) throws Exception {
		// TODO Auto-generated method stub
		List<PuntoDeVenta> listaPuntosDeVenta = puntoDeVentaRepository.findByNombreLike(nombre);
		if (!listaPuntosDeVenta.isEmpty()) {
			return listaPuntosDeVenta;
		} else {
			String logMensaje = "No se encontraron Punto de Venta con el nombre: " + nombre;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

}

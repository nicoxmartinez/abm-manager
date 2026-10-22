package com.ventas.abm.manager.service;

import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.PuntoDeVenta;
import com.ventas.abm.manager.mapper.PuntoDeVentaMapper;
import com.ventas.abm.manager.models.PuntoDeVentaDto;
import com.ventas.abm.manager.repository.PuntoDeVentaRepository;

@Service
public class PuntoDeVentaService {

	@Autowired
	PuntoDeVentaRepository puntoDeVentaRepository;

	@Autowired
	PuntoDeVentaMapper puntoDeVentaMapper;

	private static final Log logger = LogFactory.getLog(PuntoDeVentaService.class);

	public PuntoDeVenta createPuntoDeVenta(PuntoDeVenta puntoDeVenta) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Punto de Venta";
		logger.info(logMensaje);
		PuntoDeVenta puntoDeVentaNuevo = new PuntoDeVenta();
		if (puntoDeVenta.getNumeroTerminal() != null) {
			Optional<PuntoDeVenta> verificarNumeroDePunto = puntoDeVentaRepository
					.findByNumeroTerminal(puntoDeVenta.getNumeroTerminal());
			if (verificarNumeroDePunto.isPresent()) {
				logMensaje = "Existe un Punto de Venta con el número de terminal" + puntoDeVenta.getNombreTerminal();
				logger.error(logMensaje);
				throw new Exception(logMensaje);
			}
		} else {
			logMensaje = "El Número del Punto de Venta es obligatorio";
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		try {
			puntoDeVentaNuevo.setNumeroTerminal(puntoDeVenta.getNumeroTerminal());
			puntoDeVentaNuevo.setNombreTerminal(puntoDeVenta.getNombreTerminal());
			puntoDeVentaNuevo.setNumeroDeCaja(puntoDeVenta.getNumeroDeCaja());
			puntoDeVentaRepository.save(puntoDeVentaNuevo);
			logMensaje = "Registrada correctamente Tienda: " + puntoDeVentaNuevo.getNumeroTerminal();
			logger.info(logMensaje);
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new Exception(logMensaje);

		}
		return puntoDeVentaNuevo;
	}

	public PuntoDeVenta modifyPuntoDeVenta(PuntoDeVenta puntoDeVenta) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro Tienda ID: " + puntoDeVenta.getIdPuntoDeVenta();
		logger.info(logMensaje);
		Optional<PuntoDeVenta> puntoDeVentaOptional = puntoDeVentaRepository.findById(puntoDeVenta.getIdPuntoDeVenta());
		if (puntoDeVentaOptional.isPresent()) {
			PuntoDeVenta puntoDeVentaEnBd = puntoDeVentaOptional.get();
			puntoDeVentaEnBd.setNumeroTerminal(puntoDeVenta.getNumeroTerminal());
			puntoDeVentaEnBd.setNombreTerminal(puntoDeVenta.getNombreTerminal());
			puntoDeVentaEnBd.setNumeroDeCaja(puntoDeVenta.getNumeroDeCaja());
			PuntoDeVenta puntoDeVentaModificado = puntoDeVentaRepository.save(puntoDeVentaEnBd);
			logMensaje = "Modificado correctamente Punto De Venta ID: " + puntoDeVentaModificado.getNumeroTerminal();
			logger.info(logMensaje);
			return puntoDeVentaModificado;
		} else {
			logMensaje = "No existe un Punto de Venta con ID: " + puntoDeVenta.getIdPuntoDeVenta();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public PuntoDeVenta getPuntoDeVentaById(Long idPuntoDeVenta) {
		// TODO Auto-generated method stub
		Optional<PuntoDeVenta> puntoDeVentaOptional = puntoDeVentaRepository.findById(idPuntoDeVenta);
		if (puntoDeVentaOptional.isPresent()) {
			PuntoDeVenta puntoDeVenta = puntoDeVentaOptional.get();
			return puntoDeVenta;
		} else {
			logger.error("No se encontró Punto de Venta con el ID: " + idPuntoDeVenta);
			return null;
		}
	}

	public PuntoDeVentaDto getPuntoDeVentaByNumeroTerminal(Integer numeroTerminal) throws Exception {
		// TODO Auto-generated method stub
		Optional<PuntoDeVenta> puntoDeVentaOptional = puntoDeVentaRepository.findByNumeroTerminal(numeroTerminal);
		if (puntoDeVentaOptional.isPresent()) {
			PuntoDeVentaDto dto = puntoDeVentaMapper.mapearPuntoDeVentaDto(puntoDeVentaOptional.get());
			return dto;
		} else {
			String logMensaje = "No se encontró Punto de Venta con el Número de terminal: " + numeroTerminal;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public List<PuntoDeVentaDto> getPuntoDeVentaByNombreTerminal(String nombreTerminal) throws Exception {
		// TODO Auto-generated method stub
		List<PuntoDeVenta> listaPuntosDeVenta = puntoDeVentaRepository.findByNombreTerminalLike(nombreTerminal);
		if (!listaPuntosDeVenta.isEmpty()) {
			List<PuntoDeVentaDto> listaDto = puntoDeVentaMapper.mapearListaPuntoDeVentaDto(listaPuntosDeVenta);
			return listaDto;
		} else {
			String logMensaje = "No se encontraron Punto de Venta con el nombre de terminal: " + nombreTerminal;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

}

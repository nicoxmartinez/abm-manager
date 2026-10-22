package com.ventas.abm.manager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.Tienda;
import com.ventas.abm.manager.repository.TiendaRepository;

@Service
public class TiendaService {

	@Autowired
	TiendaRepository tiendaRepository;

	private static final Log logger = LogFactory.getLog(TiendaService.class);

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

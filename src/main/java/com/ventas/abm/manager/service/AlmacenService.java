package com.ventas.abm.manager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.Almacen;
import com.ventas.abm.manager.domain.Domicilio;
import com.ventas.abm.manager.domain.Tienda;
import com.ventas.abm.manager.repository.AlmacenRepository;
import com.ventas.abm.manager.repository.TiendaRepository;

import jakarta.transaction.Transactional;

@Service
public class AlmacenService {

	@Autowired
	AlmacenRepository almacenRepository;

	@Autowired
	TiendaRepository tiendaRepository;

	@Autowired
	DomicilioService domicilioService;

	private static final Log logger = LogFactory.getLog(AlmacenService.class);

	@Transactional
	public Almacen createAlmacen(Almacen almacen) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Almacén";
		logger.info(logMensaje);
		Optional<Almacen> verificarCodigoAlmacen = almacenRepository.findByCodigo(almacen.getCodigo());
		if (verificarCodigoAlmacen.isPresent()) {
			logMensaje = "Existe un registro de Almacen con el Código " + almacen.getCodigo();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		Almacen almacenNuevo = new Almacen();
		try {
			if (almacen.getTienda() != null) {
				Optional<Tienda> tiendaOptional = tiendaRepository.findById(almacen.getTienda().getIdTienda());
				if (tiendaOptional.isPresent()) {
					Tienda tienda = tiendaOptional.get();
					almacenNuevo.setTienda(tienda);
				}
			}
			almacenNuevo.setCodigo(almacen.getCodigo());
			almacenNuevo.setDescripcion(almacen.getDescripcion());
			almacenNuevo.setFechaBaja(null);
			if (almacen.getDomicilio() != null) {
				Domicilio domicilio = almacen.getDomicilio();
				Domicilio domicilioAlmacen = domicilioService.createDomicilio(domicilio);
				if (domicilioAlmacen != null) {
					almacenNuevo.setDomicilio(domicilioAlmacen);
				}
			}
			almacenRepository.save(almacenNuevo);
			logMensaje = "Registrada correctamente Almacén: " + almacenNuevo.getCodigo() + " "
					+ almacenNuevo.getDescripcion();
			logger.info(logMensaje);
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		return almacenNuevo;
	}

	@Transactional
	public Almacen modifyAlmacen(Almacen almacen) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro Almacén ID: " + almacen.getIdAlmacen();
		logger.info(logMensaje);
		Optional<Almacen> almacenOptional = almacenRepository.findById(almacen.getIdAlmacen());
		if (almacenOptional.isPresent()) {
			Almacen almacenEnBd = almacenOptional.get();
			if (almacen.getTienda() != null) {
				Optional<Tienda> tiendaOptional = tiendaRepository.findById(almacen.getTienda().getIdTienda());
				if (tiendaOptional.isPresent()) {
					Tienda tienda = tiendaOptional.get();
					almacenEnBd.setTienda(tienda);
				}
			} else {
				almacenEnBd.setTienda(null);
			}
			almacenEnBd.setCodigo(almacen.getCodigo());
			almacenEnBd.setDescripcion(almacen.getDescripcion());
			almacenEnBd.setFechaBaja(almacen.getFechaBaja());
			if (almacen.getDomicilio() != null) {
				Domicilio domicilioAlmacen = almacen.getDomicilio();

				if (domicilioAlmacen.getIdDomicilio() != null) {
					Domicilio domicilioModificado = domicilioService.modifyDomicilio(domicilioAlmacen);
					almacenEnBd.setDomicilio(domicilioModificado);
				} else {
					if (almacenEnBd.getDomicilio() != null && almacenEnBd.getDomicilio().getIdDomicilio() != null) {
						domicilioService.deleteDomicilio(almacenEnBd.getDomicilio().getIdDomicilio());
					}
					Domicilio nuevoDomicilio = domicilioService.createDomicilio(domicilioAlmacen);
					almacenEnBd.setDomicilio(nuevoDomicilio);
				}
			} else {
				if (almacenEnBd.getDomicilio() != null && almacenEnBd.getDomicilio().getIdDomicilio() != null) {
					domicilioService.deleteDomicilio(almacenEnBd.getDomicilio().getIdDomicilio());
				}
				almacenEnBd.setDomicilio(null);
			}
			Almacen almacenModificado = almacenRepository.save(almacenEnBd);
			logMensaje = "Modificado correctamente Almacén ID: " + almacenModificado.getIdAlmacen();
			logger.info(logMensaje);
			return almacenModificado;
		} else {
			logMensaje = "No existe un Almacén con ID: " + almacen.getIdAlmacen();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public Almacen deleteAlmacen(long idAlmacen) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Realizando baja de Almacén ID: " + idAlmacen;
		logger.info(logMensaje);
		Optional<Almacen> almacen = almacenRepository.findById(idAlmacen);
		if (almacen.isPresent()) {
			Almacen bajaDeAlmacen = almacen.get();
			bajaDeAlmacen.setFechaBaja(new Date());
			almacenRepository.save(bajaDeAlmacen);
			logMensaje = "Se dio de baja correctamente Almacén: " + bajaDeAlmacen.getCodigo() + " "
					+ bajaDeAlmacen.getDescripcion();
			logger.info(logMensaje);
			return bajaDeAlmacen;
		} else {
			logMensaje = "No existe un Almacén con ID: " + idAlmacen;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public Almacen getAlmacenById(Long idAlmacen) {
		// TODO Auto-generated method stub
		Almacen almacen = new Almacen();
		Optional<Almacen> almacenOptional = almacenRepository.findById(idAlmacen);
		if (almacenOptional.isPresent()) {
			almacen = almacenOptional.get();
			return almacen;
		} else {
			logger.error("No se encontró Almacén con el ID: " + idAlmacen);
			return null;
		}
	}

	public Almacen getAlmacenByCodigo(String codigo) throws Exception {
		// TODO Auto-generated method stub
		Optional<Almacen> almacenOptional = almacenRepository.findByCodigo(codigo);
		if (almacenOptional.isPresent()) {
			Almacen almacen = almacenOptional.get();
			return almacen;
		} else {
			String logMensaje = "No se encontró Almacén con el código: " + codigo;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public List<Almacen> getAlmacenByDescripcion(String descripcion) throws Exception {
		// TODO Auto-generated method stub
		List<Almacen> listaAlmacen = almacenRepository.findByDescripcionLike(descripcion);
		if (!listaAlmacen.isEmpty()) {
			return listaAlmacen;
		} else {
			String logMensaje = "No se encontraron almacenes con la descripción: " + descripcion;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

}

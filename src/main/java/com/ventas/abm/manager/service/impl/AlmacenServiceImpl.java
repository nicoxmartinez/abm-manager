package com.ventas.abm.manager.service.impl; // O puedes dejarlo en el paquete service

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
import com.ventas.abm.manager.exception.EntidadNoEncontradaException;
import com.ventas.abm.manager.exception.ErrorIntegridadException;
import com.ventas.abm.manager.exception.ErrorValorNuloException;
import com.ventas.abm.manager.exception.ExisteEntidadCodigoException;
import com.ventas.abm.manager.mapper.AlmacenMapper;
import com.ventas.abm.manager.model.AlmacenDto;
import com.ventas.abm.manager.repository.AlmacenRepository;
import com.ventas.abm.manager.repository.TiendaRepository;
import com.ventas.abm.manager.service.AlmacenService;
import com.ventas.abm.manager.service.DomicilioService;

import jakarta.transaction.Transactional;

@Service
public class AlmacenServiceImpl implements AlmacenService {

	@Autowired
	AlmacenRepository almacenRepository;

	@Autowired
	TiendaRepository tiendaRepository;

	@Autowired
	DomicilioService domicilioService;

	@Autowired
	AlmacenMapper almacenMapper;

	private static final Log logger = LogFactory.getLog(AlmacenServiceImpl.class);

	@Override
	@Transactional
	public Almacen createAlmacen(Almacen almacen) {
		String logMensaje = "Creando registro de Almacén: " + almacen.getCodigo() + " " + almacen.getDescripcion();
		logger.info(logMensaje);
		if (almacen.getIdAlmacen() != null) {
			logMensaje = "La creacion de Almacén no puede tener ID: " + almacen.getIdAlmacen();
			throw new ErrorIntegridadException(logMensaje);
		}
		if (almacen.getCodigo() != null) {
			Optional<Almacen> verificarCodigoAlmacen = almacenRepository.findByCodigo(almacen.getCodigo());
			if (verificarCodigoAlmacen.isPresent()) {
				logMensaje = "Existe un registro de Almacen con el Código: " + almacen.getCodigo();
				logger.error(logMensaje);
				throw new ExisteEntidadCodigoException(logMensaje);
			}
		} else {
			logMensaje = "El código de Almacén es requerido";
			logger.error(logMensaje);
			throw new ErrorValorNuloException(logMensaje);
		}
		Almacen almacenNuevo = new Almacen();
		try {
			if (almacen.getTienda() != null) {
				Optional<Tienda> tiendaOptional = tiendaRepository.findById(almacen.getTienda().getIdTienda());
				if (tiendaOptional.isPresent()) {
					Tienda tienda = tiendaOptional.get();
					almacenNuevo.setTienda(tienda);
				}
			} else {
				logMensaje = "Ingrese a que Tienda pertenece el Almacén";
				logger.error(logMensaje);
				throw new ErrorValorNuloException(logMensaje);
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
			} else {
				logMensaje = "Ingrese un Domicilio";
				logger.error(logMensaje);
				throw new ErrorValorNuloException(logMensaje);
			}
			almacenRepository.save(almacenNuevo);
			logMensaje = "Registrado correctamente Almacén ID: " + almacenNuevo.getIdAlmacen();
			logger.info(logMensaje);
		} catch (RuntimeException e) {
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new RuntimeException(logMensaje);
		}
		return almacenNuevo;
	}

	@Override
	@Transactional
	public Almacen modifyAlmacen(Almacen almacen) {
		String logMensaje = "Modificando registro Almacén ID: " + almacen.getIdAlmacen();
		logger.info(logMensaje);
		if (almacen.getIdAlmacen() != null) {
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
					logMensaje = "Ingrese a que Tienda pertenece el Almacén";
					logger.error(logMensaje);
					throw new ErrorValorNuloException(logMensaje);
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
				logMensaje = "Modificado correctamente Almacén: " + almacenModificado.getCodigo() + " "
						+ almacenModificado.getDescripcion();
				logger.info(logMensaje);
				return almacenModificado;
			} else {
				logMensaje = "No existe un Almacén con ID: " + almacen.getIdAlmacen();
				logger.error(logMensaje);
				throw new EntidadNoEncontradaException(logMensaje);
			}
		} else {
			logMensaje = "El Almacén ID es requerido: " + almacen.getIdAlmacen();
			logger.error(logMensaje);
			throw new ErrorValorNuloException(logMensaje);
		}
	}

	@Override
	public Almacen deleteAlmacen(long idAlmacen) {
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
			throw new EntidadNoEncontradaException(logMensaje);
		}
	}

	@Override
	public Almacen getAlmacenById(Long idAlmacen) {
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

	@Override
	public AlmacenDto getAlmacenByCodigo(String codigo) {
		Optional<Almacen> almacenOptional = almacenRepository.findByCodigo(codigo);
		if (almacenOptional.isPresent()) {
			AlmacenDto dto = almacenMapper.mapearAlmacenDto(almacenOptional.get());
			return dto;
		} else {
			String logMensaje = "No se encontró Almacén con el código: " + codigo;
			logger.error(logMensaje);
			throw new EntidadNoEncontradaException(logMensaje);
		}
	}

	@Override
	public List<AlmacenDto> getAlmacenByDescripcion(String descripcion) {
		List<Almacen> listaAlmacenes = almacenRepository.findByDescripcionLike(descripcion);
		if (!listaAlmacenes.isEmpty()) {
			List<AlmacenDto> listaDto = almacenMapper.mapearListaAlmacenDto(listaAlmacenes);
			return listaDto;
		} else {
			String logMensaje = "No se encontraron almacenes con la descripción: " + descripcion;
			logger.error(logMensaje);
			throw new EntidadNoEncontradaException(logMensaje);
		}
	}
}
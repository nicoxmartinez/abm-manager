package com.ventas.abm.manager.service.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.ECommerce;
import com.ventas.abm.manager.exception.EntidadNoEncontradaException;
import com.ventas.abm.manager.exception.ErrorIntegridadException;
import com.ventas.abm.manager.exception.ErrorValorNuloException;
import com.ventas.abm.manager.exception.ExisteEntidadCodigoException;
import com.ventas.abm.manager.repository.ECommerceRepository;
import com.ventas.abm.manager.service.ECommerceService;

@Service
public class ECommerceServiceImpl implements ECommerceService {

	@Autowired
	ECommerceRepository ecommerceRepository;

	private static final Log logger = (Log) LogFactory.getLog(ECommerceServiceImpl.class);

	@Override
	public ECommerce createECommerce(ECommerce ecommerce) {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de ECommerce: " + ecommerce.getCodigo() + " "
				+ ecommerce.getDescripcion();
		logger.info(logMensaje);
		if (ecommerce.getIdTienda() != null) {
			logMensaje = "La creacion de ECommerce no puede tener ID: " + ecommerce.getIdTienda();
			throw new ErrorIntegridadException(logMensaje);
		}
		if (ecommerce.getCodigo() != null) {
			Optional<ECommerce> verificarCodigoECommerce = ecommerceRepository.findByCodigo(ecommerce.getCodigo());
			if (verificarCodigoECommerce.isPresent()) {
				logMensaje = "Existe un registro de ECommerce con el Código: " + ecommerce.getCodigo();
				logger.info(logMensaje);
				throw new ExisteEntidadCodigoException(logMensaje);
			}
		} else {
			logMensaje = "El código de Tienda es requerido";
			logger.error(logMensaje);
			throw new ErrorValorNuloException(logMensaje);
		}
		ECommerce nuevoECommerce = new ECommerce();
		try {
			nuevoECommerce.setCodigo(ecommerce.getCodigo());
			nuevoECommerce.setDescripcion(ecommerce.getDescripcion());
			nuevoECommerce.setUsuario(ecommerce.getUsuario());
			nuevoECommerce.setEmail(ecommerce.getEmail());
			nuevoECommerce.setTelefono(ecommerce.getTelefono());
			nuevoECommerce.setDominio(ecommerce.getDominio());
			nuevoECommerce.setFechaAlta(new Date());
			nuevoECommerce.setFechaBaja(null);
			ecommerceRepository.save(nuevoECommerce);
			logMensaje = "Registrado correctamente ECommerce ID: " + nuevoECommerce.getIdTienda();
			logger.info(logMensaje);
		} catch (RuntimeException e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new RuntimeException(logMensaje);
		}
		return nuevoECommerce;
	}

	@Override
	public ECommerce modifyECommerce(ECommerce ecommerce) {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro de ECommerce ID: " + ecommerce.getIdTienda();
		if (ecommerce.getIdTienda() != null) {
			Optional<ECommerce> ecommerceOptional = ecommerceRepository.findById(ecommerce.getIdTienda());
			if (ecommerceOptional.isPresent()) {
				ECommerce ecommerceEnBd = ecommerceOptional.get();
				// Datos de Tienda
				ecommerceEnBd.setCodigo(ecommerce.getCodigo());
				ecommerceEnBd.setDescripcion(ecommerce.getDescripcion());
				ecommerceEnBd.setUsuario(ecommerce.getUsuario());
				ecommerceEnBd.setEmail(ecommerce.getEmail());
				ecommerceEnBd.setTelefono(ecommerce.getTelefono());
				ecommerceEnBd.setFechaAlta(ecommerce.getFechaAlta());
				ecommerceEnBd.setFechaBaja(ecommerce.getFechaBaja());
				ECommerce ecommerceModificado = ecommerceRepository.save(ecommerceEnBd);
				logMensaje = "Modificado correctamente Local: " + ecommerceModificado.getCodigo() + " "
						+ ecommerceModificado.getDescripcion();
				logger.info(logMensaje);
				return ecommerceModificado;
			} else {
				logMensaje = "No existe un Local con ID: " + ecommerce.getIdTienda();
				logger.error(logMensaje);
				throw new EntidadNoEncontradaException(logMensaje);
			}
		} else {
			logMensaje = "El ECommerce ID es requerido: " + ecommerce.getIdTienda();
			logger.error(logMensaje);
			throw new ErrorValorNuloException(logMensaje);
		}
	}

}

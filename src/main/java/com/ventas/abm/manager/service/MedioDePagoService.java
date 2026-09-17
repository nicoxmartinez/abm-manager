package com.ventas.abm.manager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.Financiacion;
import com.ventas.abm.manager.domain.MedioDePago;
import com.ventas.abm.manager.repository.MedioDePagoRepository;

@Service
public class MedioDePagoService {

	@Autowired
	MedioDePagoRepository medioDePagoRepository;

	@Autowired
	FinanciacionService financiacionService;

	private static final Log logger = LogFactory.getLog(MedioDePagoService.class);

	public MedioDePago createMedioDePago(MedioDePago medioDePago) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro Medio de Pago";
		logger.info(logMensaje);
		Optional<MedioDePago> verificarMedioDePago = medioDePagoRepository.findByCodigo(medioDePago.getCodigo());
		if (verificarMedioDePago.isPresent()) {
			logMensaje = "Existe un Medio de pago con Código: " + medioDePago.getCodigo();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		MedioDePago medioDePagoNuevo = new MedioDePago();
		try {
			medioDePagoNuevo.setCodigo(medioDePago.getCodigo());
			medioDePagoNuevo.setDetalle(medioDePago.getDetalle());
			medioDePagoNuevo.setAbreviatura(medioDePago.getAbreviatura());
			medioDePagoNuevo.setHabilitado(medioDePago.getHabilitado());
			medioDePagoNuevo.setCantidadMaximaCuotas(medioDePago.getCantidadMaximaCuotas());
			medioDePagoNuevo.setPermiteBonificacion(medioDePago.getPermiteBonificacion());
			medioDePagoNuevo.setTipoTarjeta(medioDePago.getTipoTarjeta());
			medioDePagoNuevo.setFechaBaja(null);
			if (medioDePago.getFinanciacion() != null) {
				Financiacion financiacion = financiacionService
						.getFinanciacionById(medioDePago.getFinanciacion().getIdFinanciacion());
				if (financiacion != null) {
					medioDePagoNuevo.setFinanciacion(financiacion);
				}
			}
			medioDePagoRepository.save(medioDePagoNuevo);
			logMensaje = "Registrada correctamente Medio de Pago: " + medioDePagoNuevo.getCodigo() + " "
					+ medioDePagoNuevo.getDetalle();
			logger.info(logMensaje);
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		return medioDePagoNuevo;
	}

	public MedioDePago modifyMedioDePago(MedioDePago medioDePago) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro Medio de Pago ID: " + medioDePago.getIdMedioPago();
		logger.info(logMensaje);
		Optional<MedioDePago> medioDePagoOptional = medioDePagoRepository.findById(medioDePago.getIdMedioPago());
		if (medioDePagoOptional.isPresent()) {
			MedioDePago medioDePagoEnBd = medioDePagoOptional.get();
			medioDePagoEnBd.setCodigo(medioDePago.getCodigo());
			medioDePagoEnBd.setDetalle(medioDePago.getDetalle());
			medioDePagoEnBd.setAbreviatura(medioDePago.getAbreviatura());
			medioDePagoEnBd.setHabilitado(medioDePago.getHabilitado());
			medioDePagoEnBd.setPermiteBonificacion(medioDePago.getPermiteBonificacion());
			medioDePagoEnBd.setTipoTarjeta(medioDePago.getTipoTarjeta());
			medioDePagoEnBd.setFechaBaja(medioDePago.getFechaBaja());
			if (medioDePago.getFinanciacion() != null) {
				Financiacion financiacion = financiacionService
						.getFinanciacionById(medioDePago.getFinanciacion().getIdFinanciacion());
				if (financiacion != null) {
					medioDePagoEnBd.setFinanciacion(financiacion);
				}
			} else {
				medioDePagoEnBd.setFinanciacion(null);
			}
			MedioDePago medioDePagoModificado = medioDePagoRepository.save(medioDePagoEnBd);
			logMensaje = "Modificado correctamente Medio de Pago ID: " + medioDePagoModificado.getIdMedioPago();
			logger.info(logMensaje);
			return medioDePagoModificado;
		} else {
			logMensaje = "No existe un Medio de Pago con ID: " + medioDePago.getIdMedioPago();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}

	}

	public MedioDePago deleteMedioDePago(Long idMedioPago) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Realizando baja de Medio de Pago ID: " + idMedioPago;
		logger.info(logMensaje);
		Optional<MedioDePago> optional = medioDePagoRepository.findById(idMedioPago);
		if (optional.isPresent()) {
			MedioDePago medioDePago = optional.get();
			medioDePago.setHabilitado(false);
			medioDePago.setFechaBaja(new Date());
			MedioDePago medioPagoDeshabilitado = medioDePagoRepository.save(medioDePago);
			return medioPagoDeshabilitado;
		} else {
			logMensaje = "No se encontró Medio Pago con el ID " + idMedioPago;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public List<MedioDePago> getAllMedioDePagoHabilitados() throws Exception {
		// TODO Auto-generated method stub
		List<MedioDePago> listaMediosDePago = medioDePagoRepository.findAllMedioPagoHabilitados();
		if (listaMediosDePago.isEmpty()) {
			String logMensaje = "No se pudo obtener la lista de Medios de Pago";
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		} else {
			return listaMediosDePago;
		}
	}

	public MedioDePago getMedioDePagoById(Long idMedioPago) throws Exception {
		// TODO Auto-generated method stub
		MedioDePago medioDePago = new MedioDePago();
		try {
			Optional<MedioDePago> medioDepagoOptional = medioDePagoRepository.findById(idMedioPago);
			if (medioDepagoOptional.isPresent()) {
				medioDePago = medioDepagoOptional.get();
			}
			return medioDePago;
		} catch (Exception e) {
			String logMensaje = "No se encontró Medio de Pago con el ID: " + idMedioPago;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public MedioDePago getMedioDePagoByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		Optional<MedioDePago> medioDePagoOptional = medioDePagoRepository.findByCodigo(codigo);
		if (medioDePagoOptional.isPresent()) {
			MedioDePago medioDePago = medioDePagoOptional.get();
			return medioDePago;
		} else {
			String logMensaje = "No se encontró Medio de Pago: " + codigo;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public List<MedioDePago> getMedioDePagoByDetalle(String detalle) throws Exception {
		// TODO Auto-generated method stub
		List<MedioDePago> listaMedioDePago = medioDePagoRepository.findByDetalleLikeAndFechaBajaIsNull(detalle);
		if (listaMedioDePago.isEmpty()) {
			String logMensaje = "No se encontró Medio de Pago: " + detalle;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		} else {	
			return listaMedioDePago;
		}
	}

}

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
import com.ventas.abm.manager.domain.TipoMedioPago;
import com.ventas.abm.manager.mapper.MedioDePagoMapper;
import com.ventas.abm.manager.models.MedioDePagoDto;
import com.ventas.abm.manager.repository.MedioDePagoRepository;

@Service
public class MedioDePagoService {

	@Autowired
	MedioDePagoRepository medioDePagoRepository;

	@Autowired
	FinanciacionService financiacionService;

	@Autowired
	MedioDePagoMapper medioDePagoMapper;

	private static final Log logger = LogFactory.getLog(MedioDePagoService.class);

	public MedioDePago createMedioDePago(MedioDePago medioDePago) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro Medio de Pago: " + medioDePago.getCodigo() + " "
				+ medioDePago.getDetalle();
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
			TipoMedioPago tipoMedioPago = medioDePago.getTipo();
			medioDePagoNuevo.setTipo(tipoMedioPago);
			medioDePagoNuevo.setHabilitado(medioDePago.getHabilitado());
			medioDePagoNuevo.setPermiteBonificacion(medioDePago.getPermiteBonificacion());
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
			e.printStackTrace();
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
			TipoMedioPago tipoMedioPago = medioDePago.getTipo();
			medioDePagoEnBd.setTipo(tipoMedioPago);
			medioDePagoEnBd.setHabilitado(medioDePago.getHabilitado());
			medioDePagoEnBd.setPermiteBonificacion(medioDePago.getPermiteBonificacion());
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

	public List<MedioDePagoDto> getAllMedioDePagoHabilitados() throws Exception {
		// TODO Auto-generated method stub
		List<MedioDePago> listaMediosDePago = medioDePagoRepository.findAllMedioPagoHabilitados();
		if (listaMediosDePago.isEmpty()) {
			String logMensaje = "No se pudo obtener la lista de Medios de Pago";
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
		List<MedioDePagoDto> listaDto = medioDePagoMapper.mapearListaMedioDePagoDto(listaMediosDePago);
		return listaDto;
	}

	public MedioDePago getMedioDePagoById(Long idMedioPago) throws Exception {
		// TODO Auto-generated method stub
		MedioDePago medioDePago = new MedioDePago();
		Optional<MedioDePago> medioDepagoOptional = medioDePagoRepository.findById(idMedioPago);
		if (medioDepagoOptional.isPresent()) {
			medioDePago = medioDepagoOptional.get();
			return medioDePago;
		} else {
			logger.error("No se encontró Medio de Pago con el ID: " + idMedioPago);
			return null;
		}
	}

	public MedioDePagoDto getMedioDePagoByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		Optional<MedioDePago> medioDePagoOptional = medioDePagoRepository.findByCodigo(codigo);
		if (medioDePagoOptional.isPresent()) {
			MedioDePagoDto dto = medioDePagoMapper.mapearMedioDePagoDto(medioDePagoOptional.get());
			return dto;
		} else {
			String logMensaje = "No se encontró Medio de Pago: " + codigo;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public List<MedioDePagoDto> getMedioDePagoByDetalle(String detalle) throws Exception {
		// TODO Auto-generated method stub
		List<MedioDePago> listaMedioDePago = medioDePagoRepository.findByDetalleLikeAndFechaBajaIsNull(detalle);
		if (!listaMedioDePago.isEmpty()) {
			List<MedioDePagoDto> listaDto = medioDePagoMapper.mapearListaMedioDePagoDto(listaMedioDePago);
			return listaDto;
		} else {
			String logMensaje = "No se encontró Medio de Pago: " + detalle;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

}

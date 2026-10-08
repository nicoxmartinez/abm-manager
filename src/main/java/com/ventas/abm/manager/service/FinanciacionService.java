package com.ventas.abm.manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ventas.abm.manager.domain.Financiacion;
import com.ventas.abm.manager.domain.PlanCuota;
import com.ventas.abm.manager.repository.FinanciacionRepository;

@Service
public class FinanciacionService {

	@Autowired
	FinanciacionRepository financiacionRepository;

	@Autowired
	PlanCuotaService planCuotaService;

	private static final Log logger = LogFactory.getLog(AlmacenService.class);

	@Transactional
	public Financiacion createFinanciacion(Financiacion financiacion) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Financiacion: " + financiacion.getCodigo() + " "
				+ financiacion.getDescripcion();
		logger.info(logMensaje);
		Integer codigoFinanciacion = financiacion.getCodigo();
		if (financiacion.getIdFinanciacion() != null) {
			logMensaje = "La creacion de financiacion no puede tener id " + financiacion.getIdFinanciacion();
			throw new Exception(logMensaje);
		}

		Optional<Financiacion> validarCodigoFinanciacion = financiacionRepository.findByCodigo(codigoFinanciacion);
		if (validarCodigoFinanciacion.isPresent()) {
			logMensaje = "Financiacion encontrada" + " codigo: " + validarCodigoFinanciacion.get().getDescripcion();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}

		Financiacion nuevaFinanciacion = new Financiacion();

		nuevaFinanciacion.setCodigo(financiacion.getCodigo());
		nuevaFinanciacion.setDescripcion(financiacion.getDescripcion());
		nuevaFinanciacion.setActiva(financiacion.getActiva());

		List<PlanCuota> listaNuevaPlanesCuotas = new ArrayList<>();
		if (financiacion.getPlanCuota() != null) {
			List<PlanCuota> listaPlanesCuotas = financiacion.getPlanCuota();
			for (PlanCuota pc : listaPlanesCuotas) {
				PlanCuota nuevoPlanCuota = planCuotaService.createPlanCuota(pc);
				listaNuevaPlanesCuotas.add(nuevoPlanCuota);
			}
			nuevaFinanciacion.setPlanCuota(listaPlanesCuotas);
		}

		nuevaFinanciacion = financiacionRepository.save(nuevaFinanciacion);
		logMensaje = "Creado correctamente " + "Financiacion: " + nuevaFinanciacion.getCodigo() + " "
				+ nuevaFinanciacion.getDescripcion();
		logger.info(logMensaje);

		return nuevaFinanciacion;
	}

	@Transactional
	public Financiacion modifyFinanciacion(Financiacion financiacion) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro de Financiacion: " + financiacion.getCodigo() + " "
				+ financiacion.getDescripcion();
		logger.info(logMensaje);
		Optional<Financiacion> financiacionDb = financiacionRepository.findById(financiacion.getIdFinanciacion());
		if (financiacionDb.isPresent()) {
			Financiacion financiacionActualizar = financiacionDb.get();
			financiacionActualizar.setCodigo(financiacion.getCodigo());
			financiacionActualizar.setDescripcion(financiacion.getDescripcion());
			financiacionActualizar.setActiva(financiacion.getActiva());

			List<PlanCuota> listaPlanescuotaActualizada = new ArrayList<>();
			if (financiacion.getPlanCuota() != null && !financiacion.getPlanCuota().isEmpty()) {
				List<PlanCuota> planesExist = financiacion.getPlanCuota().stream().filter(p -> p.getId() != null)
						.collect(Collectors.toList());
				List<PlanCuota> planesNew = financiacion.getPlanCuota().stream().filter(p -> p.getId() == null)
						.collect(Collectors.toList());

				if (planesExist != null && !planesExist.isEmpty()) {
					for (PlanCuota pc : planesExist) {
						PlanCuota pcDb = planCuotaService.updatePlanCuota(pc);
						if (pcDb == null) {
							logger.error("No se encontró el PlanCuota con id " + pc.getId());
							throw new Exception(logMensaje);
						}
						listaPlanescuotaActualizada.add(pcDb);
					}
				} else {
					for (PlanCuota pc : financiacionDb.get().getPlanCuota()) {
						planCuotaService.deletePlanCuota(pc.getId());
					}
				}
				if (planesNew != null && !planesNew.isEmpty()) {
					for (PlanCuota pc : planesNew) {
						PlanCuota pcDb = planCuotaService.createPlanCuota(pc);
						if (pcDb == null) {
							logger.error("No se encontró el PlanCuota con id " + pc.getId());
							throw new Exception(logMensaje);
						}
						listaPlanescuotaActualizada.add(pcDb);
					}
				}
				financiacionActualizar.getPlanCuota().clear();
				financiacionActualizar.setPlanCuota(listaPlanescuotaActualizada);
			}

			financiacionActualizar = financiacionRepository.save(financiacionActualizar);
			logMensaje = "Modificado correctamente " + "Financiacion: " + financiacionActualizar.getCodigo() + " "
					+ financiacionActualizar.getDescripcion();
			logger.info(logMensaje);

			return financiacionActualizar;
		} else {
			logMensaje = "Financiacion no encontrada" + " id: " + financiacion.getIdFinanciacion();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public Financiacion deleteFinanciacion(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Financiacion> getAllFinanciacionActiva() throws Exception {
		// TODO Auto-generated method stub
		List<Financiacion> listaFinanciacion = financiacionRepository.findAllFinanciacionActiva();
		if (listaFinanciacion.isEmpty()) {
			String logMensaje = "No se pudo obtener la lista de Financiacion";
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}

		return listaFinanciacion;
	}

	public Financiacion getFinanciacionById(Long idFinanciacion) {
		// TODO Auto-generated method stub
		Optional<Financiacion> financiacionOptional = financiacionRepository.findById(idFinanciacion);
		if (financiacionOptional.isPresent()) {
			Financiacion financiacion = financiacionOptional.get();
			return financiacion;
		} else {
			String logMensaje = "Financiacion no encontrada" + " id: " + idFinanciacion;
			logger.error(logMensaje);
			return null;
		}
	}

	public Financiacion getFinanciacionByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		Optional<Financiacion> financiacionOptional = financiacionRepository.findByCodigo(codigo);
		if (financiacionOptional.isPresent()) {
			Financiacion financiacion = financiacionOptional.get();
			return financiacion;
		} else {
			String logMensaje = "No se encontró Financiación: " + codigo;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public List<Financiacion> getFinanciacionByDescripcion(String descripcion) throws Exception {
		// TODO Auto-generated method stub
		List<Financiacion> financiaciones = financiacionRepository.findByDescripcion(descripcion);
		if (financiaciones.isEmpty()) {
			logger.error("No se encontró Financiación: " + descripcion);
			throw new Exception("No se encontró Financiación: " + descripcion);
		}

		return financiaciones;
	}

}

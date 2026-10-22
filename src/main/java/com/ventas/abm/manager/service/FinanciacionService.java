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

	private static final Log logger = LogFactory.getLog(FinanciacionService.class);

	@Transactional
	public Financiacion createFinanciacion(Financiacion financiacion) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Financiacion: " + financiacion.getCodigo() + " "
				+ financiacion.getDescripcion();
		logger.info(logMensaje);
		Integer codigoFinanciacion = financiacion.getCodigo();
		if (financiacion.getIdFinanciacion() != null) {
			logMensaje = "La creacion de financiacion no puede tener ID: " + financiacion.getIdFinanciacion();
			throw new Exception(logMensaje);
		}

		Optional<Financiacion> validarCodigoFinanciacion = financiacionRepository.findByCodigo(codigoFinanciacion);
		if (validarCodigoFinanciacion.isPresent()) {
			logMensaje = "Financiacion encontrada con el Codigo: " + validarCodigoFinanciacion.get().getDescripcion();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}

		Financiacion nuevaFinanciacion = new Financiacion();

		nuevaFinanciacion.setCodigo(financiacion.getCodigo());
		nuevaFinanciacion.setDescripcion(financiacion.getDescripcion());
		nuevaFinanciacion.setActiva(financiacion.getActiva());

		nuevaFinanciacion = financiacionRepository.save(nuevaFinanciacion);

		List<PlanCuota> listaNuevaPlanesCuotas = new ArrayList<>();
		if (financiacion.getPlanCuota() != null) {
			List<PlanCuota> listaPlanesCuotas = financiacion.getPlanCuota();
			for (PlanCuota pc : listaPlanesCuotas) {
				pc.setFinanciacion(nuevaFinanciacion);
				PlanCuota nuevoPlanCuota = planCuotaService.createPlanCuota(pc);
				listaNuevaPlanesCuotas.add(nuevoPlanCuota);
			}
			nuevaFinanciacion.setPlanCuota(listaNuevaPlanesCuotas);
		}
		financiacionRepository.save(nuevaFinanciacion);
		logMensaje = "Creado correctamente Financiacion: " + nuevaFinanciacion.getCodigo() + " "
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
				List<PlanCuota> planesExistentes = financiacion.getPlanCuota().stream().filter(p -> p.getId() != null)
						.collect(Collectors.toList());
				List<PlanCuota> planesCuotasNuevos = financiacion.getPlanCuota().stream().filter(p -> p.getId() == null)
						.collect(Collectors.toList());

				if (planesExistentes != null && !planesExistentes.isEmpty()) {
					for (PlanCuota pc : planesExistentes) {
						PlanCuota pcExistenteModificado = planCuotaService.updatePlanCuota(pc);
						if (pcExistenteModificado == null) {
							logger.error("No se encontró el PlanCuota con ID: " + pc.getId());
							throw new Exception(logMensaje);
						}
						listaPlanescuotaActualizada.add(pcExistenteModificado);
					}
				} else {
					for (PlanCuota pc : financiacionDb.get().getPlanCuota()) {
						planCuotaService.deletePlanCuota(pc.getId());
					}
				}
				if (planesCuotasNuevos != null && !planesCuotasNuevos.isEmpty()) {
					for (PlanCuota pc : planesCuotasNuevos) {
						pc.setFinanciacion(financiacion);
						PlanCuota pcNuevo = planCuotaService.createPlanCuota(pc);
						if (pcNuevo == null) {
							logger.error("No se encontró el PlanCuota con ID: " + pc.getId());
							throw new Exception(logMensaje);
						}
						listaPlanescuotaActualizada.add(pcNuevo);
					}
				}
				financiacionActualizar.getPlanCuota().clear();
				financiacionActualizar.setPlanCuota(listaPlanescuotaActualizada);
			}

			financiacionActualizar = financiacionRepository.save(financiacionActualizar);
			logMensaje = "Modificado correctamente Financiacion: " + financiacionActualizar.getCodigo() + " "
					+ financiacionActualizar.getDescripcion();
			logger.info(logMensaje);

			return financiacionActualizar;
		} else {
			logMensaje = "Financiacion no encontrada ID: " + financiacion.getIdFinanciacion();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public Financiacion deleteFinanciacion(Long idFinanciacion) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Realizando baja de Financiación ID: " + idFinanciacion;
		logger.info(logMensaje);
		Optional<Financiacion> financiacion = financiacionRepository.findById(idFinanciacion);
		if (financiacion.isPresent()) {
			Financiacion bajaDeFinanciacion = financiacion.get();
			bajaDeFinanciacion.setActiva(false);
			financiacionRepository.save(bajaDeFinanciacion);
			logMensaje = "Se dio de baja correctamente Almacén: " + bajaDeFinanciacion.getCodigo() + " "
					+ bajaDeFinanciacion.getDescripcion();
			logger.info(logMensaje);
			return bajaDeFinanciacion;
		} else {
			logMensaje = "No existe un Financiación con ID: " + idFinanciacion;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}

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
			String logMensaje = "Financiacion no encontrada ID: " + idFinanciacion;
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

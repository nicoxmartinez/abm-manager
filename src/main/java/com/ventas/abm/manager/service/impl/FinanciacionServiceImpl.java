package com.ventas.abm.manager.service.impl;

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
import com.ventas.abm.manager.exception.EntidadNoEncontradaException;
import com.ventas.abm.manager.exception.ErrorIntegridadException;
import com.ventas.abm.manager.exception.ErrorValorNuloException;
import com.ventas.abm.manager.exception.ExisteEntidadCodigoException;
import com.ventas.abm.manager.mapper.FinanciacionMapper;
import com.ventas.abm.manager.model.FinanciacionDto;
import com.ventas.abm.manager.repository.FinanciacionRepository;
import com.ventas.abm.manager.service.FinanciacionService;
import com.ventas.abm.manager.service.PlanCuotaService;

@Service
public class FinanciacionServiceImpl implements FinanciacionService {

	@Autowired
	FinanciacionRepository financiacionRepository;

	@Autowired
	PlanCuotaService planCuotaService;

	@Autowired
	FinanciacionMapper financiacionMapper;

	private static final Log logger = LogFactory.getLog(FinanciacionServiceImpl.class);

	@Override
	@Transactional
	public Financiacion createFinanciacion(Financiacion financiacion) {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Financiacion: " + financiacion.getCodigo() + " "
				+ financiacion.getDescripcion();
		logger.info(logMensaje);
		if (financiacion.getIdFinanciacion() != null) {
			logMensaje = "La creacion de financiacion no puede tener ID: " + financiacion.getIdFinanciacion();
			throw new ErrorIntegridadException(logMensaje);
		}
		if (financiacion.getCodigo() != null) {
			Integer codigoFinanciacion = financiacion.getCodigo();
			Optional<Financiacion> validarCodigoFinanciacion = financiacionRepository.findByCodigo(codigoFinanciacion);
			if (validarCodigoFinanciacion.isPresent()) {
				logMensaje = "Financiacion encontrada con el Codigo: "
						+ validarCodigoFinanciacion.get().getDescripcion();
				logger.error(logMensaje);
				throw new ExisteEntidadCodigoException(logMensaje);
			}
		} else {
			logMensaje = "El código de Financiación es requerido";
			logger.error(logMensaje);
			throw new ErrorValorNuloException(logMensaje);
		}
		Financiacion nuevaFinanciacion = new Financiacion();
		try {
			// Datos Financiacion
			nuevaFinanciacion.setCodigo(financiacion.getCodigo());
			nuevaFinanciacion.setDescripcion(financiacion.getDescripcion());
			nuevaFinanciacion.setActiva(financiacion.getActiva());
			nuevaFinanciacion = financiacionRepository.save(nuevaFinanciacion);
			// Datos de Plan Cuotas
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
			logMensaje = "Creado correctamente Financiacion: " + nuevaFinanciacion.getIdFinanciacion();
			logger.info(logMensaje);
		} catch (RuntimeException e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new RuntimeException(logMensaje);
		}
		return nuevaFinanciacion;
	}

	@Override
	@Transactional
	public Financiacion modifyFinanciacion(Financiacion financiacion) {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro de Financiacion: " + financiacion.getCodigo() + " "
				+ financiacion.getDescripcion();
		logger.info(logMensaje);
		if (financiacion.getIdFinanciacion() != null) {
			Optional<Financiacion> financiacionOptional = financiacionRepository
					.findById(financiacion.getIdFinanciacion());
			if (financiacionOptional.isPresent()) {
				// Datos de Financiacion
				Financiacion financiacionEnBd = financiacionOptional.get();
				financiacionEnBd.setCodigo(financiacion.getCodigo());
				financiacionEnBd.setDescripcion(financiacion.getDescripcion());
				financiacionEnBd.setActiva(financiacion.getActiva());
				// Datos de Plan Cuotas
				if (financiacion.getPlanCuota() != null && !financiacion.getPlanCuota().isEmpty()) {
					List<PlanCuota> listaPlanescuotaActualizada = new ArrayList<>();
					// Coleccion de Plan cuotas en DB
					List<PlanCuota> planesCuotasActualizar = financiacion.getPlanCuota().stream()
							.filter(pc -> pc.getId() != null).collect(Collectors.toList());
					// Nueva coleccion de Plan cuotas
					List<PlanCuota> planesCuotasNuevos = financiacion.getPlanCuota().stream()
							.filter(pc -> pc.getId() == null).collect(Collectors.toList());
					// Plan cuotas descartados
					List<PlanCuota> planesParaEliminar = financiacionEnBd.getPlanCuota().stream().filter(
							pDb -> financiacion.getPlanCuota().stream().noneMatch(pc -> pDb.getId().equals(pc.getId())))
							.collect(Collectors.toList());
					// Limpiar lista existente
					financiacionEnBd.getPlanCuota().clear();
					// Elimina Planes cuotas descartados
					if (planesParaEliminar != null && !planesParaEliminar.isEmpty()) {
						for (PlanCuota pc : planesParaEliminar) {
							planCuotaService.deletePlanCuota(pc.getId());
						}
					}
					// Modifica Planes cuotas existentes
					if (planesCuotasActualizar != null && !planesCuotasActualizar.isEmpty()) {
						for (PlanCuota pc : planesCuotasActualizar) {
							PlanCuota pcExistenteModificado = planCuotaService.updatePlanCuota(pc);
							if (pcExistenteModificado == null) {
								logger.error("No se encontró el PlanCuota con ID: " + pc.getId());
								throw new EntidadNoEncontradaException(logMensaje);
							}
							listaPlanescuotaActualizada.add(pcExistenteModificado);
						}
					}
					// Crea Planes cuota nuevos
					if (planesCuotasNuevos != null && !planesCuotasNuevos.isEmpty()) {
						for (PlanCuota pc : planesCuotasNuevos) {
							pc.setFinanciacion(financiacion);
							PlanCuota pcNuevo = planCuotaService.createPlanCuota(pc);
							if (pcNuevo == null) {
								logger.error("No se creo el PlanCuota con ID: " + pc.getCantidadCuotas());
								throw new RuntimeException(logMensaje);
							}
							listaPlanescuotaActualizada.add(pcNuevo);
						}
					}
					financiacionEnBd.setPlanCuota(listaPlanescuotaActualizada);
				} else if (financiacionEnBd.getPlanCuota() != null && !financiacionEnBd.getPlanCuota().isEmpty()) {
					// limpia la lista de Planes Cuota en BD
					List<PlanCuota> limpiarPlanesCuota = financiacionEnBd.getPlanCuota();
					for (PlanCuota pc : limpiarPlanesCuota) {
						planCuotaService.deletePlanCuota(pc.getId());
					}
					financiacionEnBd.getPlanCuota().clear();
				}
				Financiacion financiacionModificado = financiacionRepository.save(financiacionEnBd);
				logMensaje = "Modificado correctamente Financiacion: " + financiacionModificado.getCodigo() + " "
						+ financiacionModificado.getDescripcion();
				logger.info(logMensaje);

				return financiacionModificado;
			} else {
				logMensaje = "Financiacion no encontrada ID: " + financiacion.getIdFinanciacion();
				logger.error(logMensaje);
				throw new EntidadNoEncontradaException(logMensaje);
			}
		} else {
			logMensaje = "El Financiación ID es requerido: " + financiacion.getIdFinanciacion();
			logger.error(logMensaje);
			throw new ErrorValorNuloException(logMensaje);
		}
	}

	@Override
	public Financiacion deleteFinanciacion(Long idFinanciacion) {
		// TODO Auto-generated method stub
		String logMensaje = "Realizando baja de Financiación ID: " + idFinanciacion;
		logger.info(logMensaje);
		Optional<Financiacion> financiacion = financiacionRepository.findById(idFinanciacion);
		if (financiacion.isPresent()) {
			Financiacion bajaDeFinanciacion = financiacion.get();
			bajaDeFinanciacion.setActiva(false);
			financiacionRepository.save(bajaDeFinanciacion);
			logMensaje = "Se dio de baja correctamente Financiación: " + bajaDeFinanciacion.getCodigo() + " "
					+ bajaDeFinanciacion.getDescripcion();
			logger.info(logMensaje);
			return bajaDeFinanciacion;
		} else {
			logMensaje = "No existe un Financiación con ID: " + idFinanciacion;
			logger.error(logMensaje);
			throw new EntidadNoEncontradaException(logMensaje);
		}

	}

	@Override
	public List<FinanciacionDto> getAllFinanciacionActiva() {
		// TODO Auto-generated method stub
		List<Financiacion> listaFinanciacion = financiacionRepository.findAllFinanciacionActiva();
		if (listaFinanciacion.isEmpty()) {
			String logMensaje = "No se pudo obtener la lista de Financiacion";
			logger.error(logMensaje);
			throw new RuntimeException(logMensaje);
		}
		List<FinanciacionDto> listaDto = financiacionMapper.mapearListaFinanciacionDto(listaFinanciacion);
		return listaDto;
	}

	@Override
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

	@Override
	public FinanciacionDto getFinanciacionByCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		Optional<Financiacion> financiacionOptional = financiacionRepository.findByCodigo(codigo);
		if (financiacionOptional.isPresent()) {
			FinanciacionDto dto = financiacionMapper.mapearFinanciacionDto(financiacionOptional.get());
			return dto;
		} else {
			String logMensaje = "No se encontró Financiación: " + codigo;
			logger.error(logMensaje);
			throw new EntidadNoEncontradaException(logMensaje);
		}
	}

	@Override
	public List<FinanciacionDto> getFinanciacionByDescripcion(String descripcion) {
		// TODO Auto-generated method stub
		List<Financiacion> financiaciones = financiacionRepository.findByDescripcion(descripcion);
		if (!financiaciones.isEmpty()) {
			List<FinanciacionDto> listaDto = financiacionMapper.mapearListaFinanciacionDto(financiaciones);
			return listaDto;
		} else {
			String logMensaje = "No se encontró Financiación: " + descripcion;
			logger.error(logMensaje);
			throw new EntidadNoEncontradaException(logMensaje);
		}

	}

}

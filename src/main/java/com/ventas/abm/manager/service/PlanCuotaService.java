package com.ventas.abm.manager.service;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.PlanCuota;
import com.ventas.abm.manager.repository.PlanCuotaRepository;

@Service
public class PlanCuotaService {

	@Autowired
	PlanCuotaRepository planCuotaRepository;

	private static final Log logger = LogFactory.getLog(PlanCuotaService.class);

	public PlanCuota createPlanCuota(PlanCuota planCuota) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro de Plan cuota " + planCuota.getCantidadCuotas();
		logger.info(logMensaje);
		PlanCuota planCuotaNuevo = new PlanCuota();
		try {
			planCuotaNuevo.setCantidadCuotas(planCuota.getCantidadCuotas());
			planCuotaNuevo.setInteres(planCuota.getInteres());
			planCuotaNuevo.setFinanciacion(planCuota.getFinanciacion());
			planCuotaRepository.save(planCuotaNuevo);
			logMensaje = "Registrada correctamente ID: " + planCuotaNuevo.getId();
			logger.info(logMensaje);
			return planCuotaNuevo;
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = "Ocurrió un error en el Plan Cuota: " + e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new Exception(logMensaje);
		}
	}

	public PlanCuota updatePlanCuota(PlanCuota planCuota) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro Plan cuota ID: " + planCuota.getId();
		logger.info(logMensaje);
		Optional<PlanCuota> optional = planCuotaRepository.findById(planCuota.getId());
		if (!optional.isPresent()) {
			logMensaje = "No existe un Plan cuota con ID: " + planCuota.getId();
			logger.info(logMensaje);
			throw new Exception(logMensaje);
		}
		PlanCuota planCuotaEnBd = optional.get();
		try {
			planCuotaEnBd.setCantidadCuotas(planCuota.getCantidadCuotas());
			planCuotaEnBd.setInteres(planCuota.getInteres());
			planCuotaRepository.save(planCuotaEnBd);
			logMensaje = "Modificado correctamente ID Plan Cuota: " + planCuotaEnBd.getId();
			logger.info(logMensaje);
			return planCuotaEnBd;
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = "Ocurrio un error en el Plan Cuota: " + e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new Exception(logMensaje);
		}
	}

	public void deletePlanCuota(Long id) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Eliminando Plan Cuota ID: " + id;
		try {
			Optional<PlanCuota> planOptional = planCuotaRepository.findById(id);
			if (planOptional.isPresent()) {
				planCuotaRepository.delete(planOptional.get());
			}
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = "Ocurrio un error en el Plan Cuota: " + e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new Exception(logMensaje);
		}

	}

	public PlanCuota getPlanCuotaById(Long id) throws Exception {
		// TODO Auto-generated method stub
		PlanCuota planCuota = new PlanCuota();
		try {
			Optional<PlanCuota> planOptional = planCuotaRepository.findById(id);
			if (planOptional.isPresent()) {
				planCuota = planOptional.get();
			}
			return planCuota;
		} catch (Exception e) {
			// TODO: handle exception
			String logMensaje = e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new Exception(logMensaje);
		}
	}

}

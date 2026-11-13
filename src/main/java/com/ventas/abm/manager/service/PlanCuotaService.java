package com.ventas.abm.manager.service;

import com.ventas.abm.manager.domain.PlanCuota;

public interface PlanCuotaService {

	PlanCuota createPlanCuota(PlanCuota planCuota) throws Exception;

	PlanCuota updatePlanCuota(PlanCuota planCuota) throws Exception;

	void deletePlanCuota(Long id) throws Exception;

	PlanCuota getPlanCuotaById(Long id) throws Exception;

}

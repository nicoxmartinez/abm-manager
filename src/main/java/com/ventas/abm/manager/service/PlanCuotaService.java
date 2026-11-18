package com.ventas.abm.manager.service;

import com.ventas.abm.manager.domain.PlanCuota;

public interface PlanCuotaService {

	PlanCuota createPlanCuota(PlanCuota planCuota);

	PlanCuota updatePlanCuota(PlanCuota planCuota);

	void deletePlanCuota(Long id);

	PlanCuota getPlanCuotaById(Long id);

}

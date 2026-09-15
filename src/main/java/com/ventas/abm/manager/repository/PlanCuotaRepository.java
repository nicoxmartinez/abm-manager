package com.ventas.abm.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.PlanCuota;

@Repository
public interface PlanCuotaRepository extends JpaRepository<PlanCuota, Long> {

}

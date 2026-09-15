package com.ventas.abm.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.Financiacion;

@Repository
public interface FinanciacionRepository extends JpaRepository<Financiacion, Long>{

}

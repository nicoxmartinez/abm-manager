package com.ventas.abm.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.PuntoDeVenta;

@Repository
public interface PuntoDeVentaRepository extends JpaRepository<PuntoDeVenta, Long> {

}

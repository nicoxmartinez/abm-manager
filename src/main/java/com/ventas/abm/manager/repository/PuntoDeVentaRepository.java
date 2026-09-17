package com.ventas.abm.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.PuntoDeVenta;

@Repository
public interface PuntoDeVentaRepository extends JpaRepository<PuntoDeVenta, Long> {
	
	Optional<PuntoDeVenta> findByNumero(Integer numero);
	
	@Query("SELECT p FROM PuntoDeVenta p WHERE p.nombre LIKE %:nombre%")
    List<PuntoDeVenta> findByNombreLike(@Param("nombre") String nombre);

}

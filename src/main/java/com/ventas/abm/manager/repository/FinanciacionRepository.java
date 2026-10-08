package com.ventas.abm.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.Financiacion;

@Repository
public interface FinanciacionRepository extends JpaRepository<Financiacion, Long>{
	
	@Query("SELECT f FROM Financiacion f WHERE f.activa = true ORDER BY f.codigo")
	List<Financiacion> findAllFinanciacionActiva();
	
	@Query("SELECT f FROM Financiacion f WHERE f.codigo = :codigo")
    Optional<Financiacion> findByCodigo(@Param("codigo") Integer codigo);

	@Query("SELECT f FROM Financiacion f WHERE f.descripcion LIKE %:descripcion%")
	List<Financiacion> findByDescripcion(@Param("descripcion") String descripcion);

}

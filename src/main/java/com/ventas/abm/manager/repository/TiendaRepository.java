package com.ventas.abm.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {	

	Optional<Tienda> findByCodigo(String codigo);

	@Query("SELECT a FROM Tienda a WHERE a.descripcion LIKE %:descripcion%")
	List<Tienda> findByDescripcionLike(@Param("descripcion") String descripcion);

}

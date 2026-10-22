package com.ventas.abm.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

	@Query("SELECT v FROM Vendedor v WHERE v.nombre LIKE %:nombre%")
	List<Vendedor> findByNombre(@Param("nombre") String nombre);

	@Query("SELECT v FROM Vendedor v WHERE v.apellido LIKE %:apellido%")
	List<Vendedor> findByApellido(@Param("apellido") String apellido);

	Vendedor findByCodigo(Integer codigo);
}

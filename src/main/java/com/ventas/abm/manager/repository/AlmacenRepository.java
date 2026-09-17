package com.ventas.abm.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.Almacen;

@Repository
public interface AlmacenRepository extends CrudRepository<Almacen, Long>{

	Optional<Almacen> findByCodigo(String codigo);

	@Query("SELECT a FROM Almacen a WHERE a.descripcion LIKE %:descripcion%")
	List<Almacen> findByDescripcionLike(@Param("descripcion") String descripcion);

}

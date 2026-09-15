package com.ventas.abm.manager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.Domicilio;

@Repository
public interface DomicilioRepository extends CrudRepository<Domicilio, Long> {
	
	List<Domicilio> findByDireccionLike(String direccion);

}

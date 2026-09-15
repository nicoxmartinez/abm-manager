package com.ventas.abm.manager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.Almacen;

@Repository
public interface AlmacenRepository extends CrudRepository<Almacen, Long>{

}

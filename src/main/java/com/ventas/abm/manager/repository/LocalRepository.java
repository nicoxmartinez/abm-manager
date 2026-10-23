package com.ventas.abm.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

	Optional<Local> findByCodigo(String codigo);

}

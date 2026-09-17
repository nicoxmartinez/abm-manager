package com.ventas.abm.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ventas.abm.manager.domain.MedioDePago;

@Repository
public interface MedioDePagoRepository extends JpaRepository<MedioDePago, Long> {

	@Query("SELECT m FROM MedioDePago m WHERE m.habilitado IS TRUE")
	List<MedioDePago> findAllMedioPagoHabilitados();

	Optional<MedioDePago> findByCodigo(int codigo);

	@Query("SELECT m FROM MedioDePago m WHERE m.detalle LIKE %:detalle%")
	List<MedioDePago> findByDetalleLikeAndFechaBajaIsNull(@Param("detalle") String detalle);

}

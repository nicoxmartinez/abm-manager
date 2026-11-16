package com.ventas.abm.manager.service;

import com.ventas.abm.manager.domain.Domicilio;

public interface DomicilioService {

	Domicilio createDomicilio(Domicilio domicilio);

	Domicilio modifyDomicilio(Domicilio domicilio);

	void deleteDomicilio(Long idDomicilio);

	Domicilio getDomicilioById(Long idDomicilio);

}
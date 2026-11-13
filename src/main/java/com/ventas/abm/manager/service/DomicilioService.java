package com.ventas.abm.manager.service;

import com.ventas.abm.manager.domain.Domicilio;

public interface DomicilioService {

	Domicilio createDomicilio(Domicilio domicilio) throws Exception;

	Domicilio modifyDomicilio(Domicilio domicilio) throws Exception;

	void deleteDomicilio(Long idDomicilio);

	Domicilio getDomicilioById(Long idDomicilio);

}

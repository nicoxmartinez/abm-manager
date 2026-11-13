package com.ventas.abm.manager.service;

import com.ventas.abm.manager.domain.Local;

public interface LocalService {

	Local createLocal(Local local) throws Exception;

	Local modifyLocal(Local local) throws Exception;

	Local getLocalById(Long idTienda);

}

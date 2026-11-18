package com.ventas.abm.manager.service;

import com.ventas.abm.manager.domain.Local;

public interface LocalService {

	Local createLocal(Local local);

	Local modifyLocal(Local local);

	Local getLocalById(Long idTienda);

}

package com.ventas.abm.manager.service;

import java.util.List;
import com.ventas.abm.manager.domain.Almacen;
import com.ventas.abm.manager.model.AlmacenDto;

public interface AlmacenService {

    Almacen createAlmacen(Almacen almacen);

    Almacen modifyAlmacen(Almacen almacen);

    Almacen deleteAlmacen(long idAlmacen);

    Almacen getAlmacenById(Long idAlmacen);

    AlmacenDto getAlmacenByCodigo(String codigo);

    List<AlmacenDto> getAlmacenByDescripcion(String descripcion);
}
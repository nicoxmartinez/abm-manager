package com.ventas.abm.manager.service;

import java.util.List;
import com.ventas.abm.manager.domain.Almacen;
import com.ventas.abm.manager.models.AlmacenDto;

public interface AlmacenService {

    Almacen createAlmacen(Almacen almacen) throws Exception;

    Almacen modifyAlmacen(Almacen almacen) throws Exception;

    Almacen deleteAlmacen(long idAlmacen) throws Exception;

    Almacen getAlmacenById(Long idAlmacen);

    AlmacenDto getAlmacenByCodigo(String codigo) throws Exception;

    List<AlmacenDto> getAlmacenByDescripcion(String descripcion) throws Exception;
}
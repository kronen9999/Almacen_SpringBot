package com.steven.almacen.services.sucursales;

import com.steven.almacen.dto.sucursales.SucursalRequest;
import com.steven.almacen.dto.sucursales.SucursalResponse;
import com.steven.almacen.entities.Sucursal;

import java.util.List;

public interface SucursalService {

    List<SucursalResponse> listar();

    SucursalResponse obtenerPorId(Long id);

    SucursalResponse registrar (SucursalRequest request);

    SucursalResponse actualizar (SucursalRequest request, Long id);

    void eliminar (Long id);
}

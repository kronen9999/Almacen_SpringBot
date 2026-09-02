package com.steven.almacen.mappers;


import com.steven.almacen.dto.sucursales.SucursalRequest;
import com.steven.almacen.dto.sucursales.SucursalResponse;
import com.steven.almacen.entities.Sucursal;
import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {

    public Sucursal requestAEntidad(SucursalRequest request)
    {
        if (request==null)return null;

        return Sucursal.builder().nombre(request.nombre().trim()).direccion(request.direccion().trim()).build();
    }

    public SucursalResponse entidadAResponse(Sucursal sucursal)
    {
        if (sucursal==null)return null;

        return new SucursalResponse(sucursal.getId(),sucursal.getNombre().trim(),sucursal.getDireccion().trim());
    }
}

package com.steven.almacen.mappers;


import com.steven.almacen.dto.ventas.DetalleVentaResponse;
import com.steven.almacen.dto.ventas.VentaRequest;
import com.steven.almacen.dto.ventas.VentaResponse;
import com.steven.almacen.entities.DetalleVenta;
import com.steven.almacen.entities.Sucursal;
import com.steven.almacen.entities.Venta;
import com.steven.almacen.enums.EstadoVenta;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Component
public class VentaMapper {

    private final SucursalMapper sucursalMapper;
    private final DetalleMapper detalleMapper;



    public Venta requestAEntidad(EstadoVenta estadoVenta, LocalDate fecha, Sucursal sucursal, List<DetalleVenta> detalleVentas)
    {

  Venta venta = Venta.builder().estadoVenta(estadoVenta).fecha(fecha).sucursal(sucursal).build();

  detalleVentas.forEach(venta::agregarDetalle);

   return venta;
    }

    public VentaResponse entidadAResponse(Venta venta)
    {

        if (venta==null)  return null;

        List <DetalleVentaResponse> detalles = venta.getDetalleVentas().stream().map(detalleMapper::entidadAResponse).toList();


        return  new VentaResponse(venta.getId()
                ,venta.getFecha().toString()
                ,venta.getEstadoVenta().toString()
                ,sucursalMapper.entidadAResponse(venta.getSucursal()),detalles,venta.getTotal()
                );

    }

}

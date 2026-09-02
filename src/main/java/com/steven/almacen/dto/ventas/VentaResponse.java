package com.steven.almacen.dto.ventas;

import com.steven.almacen.dto.sucursales.SucursalResponse;
import com.steven.almacen.entities.DetalleVenta;
import com.steven.almacen.entities.Sucursal;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Schema(description = "Datos de una venta")
public record VentaResponse (

        @Schema(description = "Identificador de la venta",example = "1")
        Long id,
        @Schema(description = "Fecha de la venta",example = "02/09/2026")
        String fecha,
        @Schema(description = "Estado de la venta",example = "Registrada")
        String estado,
        @Schema(description = "Sucursal donde se realizo la venta")
        SucursalResponse sucursal,
        @Schema(description = "Lista de productos de la venta")
        List<DetalleVentaResponse> detalles,
        @Schema(description = "Total de la venta ", example = "1500.00")
        BigDecimal total






){
}

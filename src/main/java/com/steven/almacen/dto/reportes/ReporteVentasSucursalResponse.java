package com.steven.almacen.dto.reportes;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;


public record ReporteVentasSucursalResponse(

        @Schema(name = "Id de la sucursal",example = "2")
        Long idSucursal,
        @Schema(name = "Nombre de la sucursal",example = "Las lomas")
        String nombre,
        @Schema(name = "totalFacturado",example = "2")
        BigDecimal totalFacturado,
        @Schema(name = "Id de la sucursal",example = "2")
        Long cantidadProductosVendidos
) {
}

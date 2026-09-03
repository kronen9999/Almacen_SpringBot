package com.steven.almacen.dto.sucursales;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion sobre una sucursal")
public record SucursalResponse (
        @Schema(description = "Id de la sucursal",example = "1")
        Long id,
        @Schema(description = "Nombre de la sucursal",example = "Central de Abarrotes Garcia")
        String nombre,
        @Schema(description = "Direccion de la sucursal",example = "Altavista de Arreaga #4")
        String direccion
){
}

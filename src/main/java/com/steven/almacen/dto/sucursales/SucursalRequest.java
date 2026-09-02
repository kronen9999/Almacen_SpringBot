package com.steven.almacen.dto.sucursales;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Schema(description = "Datos nesesarios para crear o actualizar una sucursal")
public record SucursalRequest(

        @Schema(description = "Nombre de la sucursal ", example = "Sucursal del norte")
        @NotBlank(message = "El nombre no puede estar vacio")
        @Size(min = 5,max = 50, message = "El nombre debe tener entre 5 y 50 caracteres")
        String nombre,

        @Schema(description = "Direccion de la sucursal ",example = "Calle 5 #10")
        @NotBlank(message = "La direccion no puede estar vacia")
        @Size(min = 5,max = 150,message = "La direccion debe de tener entre 5 y 150 caracteres")
        String direccion
) {
}

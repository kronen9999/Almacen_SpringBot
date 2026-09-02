package com.steven.almacen.dto.productos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
@Schema(description = "Informacion de un producto")
public record ProductosResponse (

        @Schema(description = "Id del producto positivo",example = "1")
        Long id,

        @Schema(description = "Nombre del producto",example = "Ps4")
        String Nombre,

        @Schema(description = "Categoria del producto",example = "Electrónica")
        String categoria,

        @Schema(description = "Percio del producto",example = "7388.21")
        BigDecimal precio,

        @Schema(description = "Cantidad en existencia del producto",example = "3828")
        Integer cantidad
){
}

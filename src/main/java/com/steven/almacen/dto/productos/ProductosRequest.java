package com.steven.almacen.dto.productos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


@Schema(description = "Datos nesesarios para crear o actualizar un producto")
public record  ProductosRequest(

        @Schema(description = "Nombre del producto ",example = "Televisor")
        @NotBlank(message = "El nombre es requerido")
        @Size(min = 5,max = 50,message = "El nombre debe tener entre 5 y 30 caracteres")
        String Nombre,

        @Schema(description = "Categoria del producto",example = "Electrónica")
        @NotBlank(message = "La categoria es requerida")
        String categoria,

        @Schema(description = "Precio del producto",example = "15999.99")
        @NotNull(message = "El precio es requerido")
        @Positive(message = "El precio debe de ser positivo")

        BigDecimal precio,
        @Schema(description = "Cantidad disponible del producto",example = "300")
        @NotNull(message = "La cantidad es requerida")
        @Positive(message = "La cantidad  debe de ser positiva")
        Integer cantidad
) {
}

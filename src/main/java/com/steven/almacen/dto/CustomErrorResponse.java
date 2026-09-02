package com.steven.almacen.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


public record CustomErrorResponse(
       int codigo,
       String mensaje
) {
}

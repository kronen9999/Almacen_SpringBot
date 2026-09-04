package com.steven.almacen.controllers;

import com.steven.almacen.dto.reportes.ReporteVentasSucursalResponse;
import com.steven.almacen.services.reportes.ReportesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@Tag(name = "Reportes",description = "Reportes del proyecto")
public class ReportesController {

    private final ReportesService reportesService;

    @GetMapping("/ventas-sucursal")
    public ResponseEntity<List<ReporteVentasSucursalResponse>> reporteVentasPorSucursal() {
        return ResponseEntity.ok(reportesService.reporteVentasPorSucursal());
    }
}

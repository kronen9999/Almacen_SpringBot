package com.steven.almacen.services.reportes;

import com.steven.almacen.dto.reportes.ReporteVentasSucursalResponse;

import java.util.List;

public interface ReportesService {

    List<ReporteVentasSucursalResponse> reporteVentasPorSucursal();
}

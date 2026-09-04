package com.steven.almacen.services.reportes;

import com.steven.almacen.dto.reportes.ReporteVentasSucursalResponse;
import com.steven.almacen.enums.EstadoVenta;
import com.steven.almacen.repositories.VentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReportesServiceImp implements ReportesService {

    private final VentaRepository ventaRepository;

    @Override
    public List<ReporteVentasSucursalResponse> reporteVentasPorSucursal() {
        log.info("Generando reporte de ventas");
        return ventaRepository.reporteVentasPorSucursal(EstadoVenta.REGISTRADA);
    }
}

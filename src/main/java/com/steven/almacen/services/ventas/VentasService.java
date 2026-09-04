package com.steven.almacen.services.ventas;

import com.steven.almacen.dto.ventas.VentaRequest;
import com.steven.almacen.dto.ventas.VentaResponse;

import java.util.List;

public interface VentasService {

    List<VentaResponse> listar();

    List<VentaResponse> listarCanceladas();

    VentaResponse obtenerPorIdActiva(Long id);

    VentaResponse registrar (VentaRequest request);

    VentaResponse cancelar(Long id);


}

package com.steven.almacen.services.productos;

import com.steven.almacen.dto.productos.ProductosRequest;
import com.steven.almacen.dto.productos.ProductosResponse;
import com.steven.almacen.repositories.ProductoRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductosService {

    List<ProductosResponse> listar(String nombre, String categoria, BigDecimal precioMin,BigDecimal precioMax);

    ProductosResponse ObtenerPorId(Long id);

    ProductosResponse registrar(ProductosRequest request);

    ProductosResponse actualizar (ProductosRequest request,Long id);

    void eliminar (Long id);

}

package com.steven.almacen.mappers;

import com.steven.almacen.dto.productos.ProductosRequest;
import com.steven.almacen.dto.productos.ProductosResponse;
import com.steven.almacen.entities.Producto;
import com.steven.almacen.enums.Categoria;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto requestAEntidad(ProductosRequest request, Categoria categoria)
    {
        if (request==null)
        {
          return null;
        }

        return Producto.builder().nombre(request.Nombre().trim())
                .categoria(categoria)
                .precio(request.precio())
                .cantidad(request.cantidad())
                .build();
    }

    public ProductosResponse entidadAResponse(Producto producto)
    {
        if (producto==null)
        {
            return null;
        }

        return new ProductosResponse(
          producto.getId(),
          producto.getNombre(),
          producto.getCategoria().getDescripcion(),
                producto.getPrecio(),
                producto.getCantidad());
    }
}

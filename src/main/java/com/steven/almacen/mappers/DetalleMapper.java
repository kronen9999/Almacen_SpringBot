package com.steven.almacen.mappers;

import com.steven.almacen.dto.ventas.DetalleVentaRequest;
import com.steven.almacen.dto.ventas.DetalleVentaResponse;
import com.steven.almacen.entities.DetalleVenta;
import com.steven.almacen.entities.Producto;
import org.springframework.stereotype.Component;

@Component
public class DetalleMapper {

    public DetalleVentaResponse entidadAResponse(DetalleVenta detalleVenta)
    {

        if (detalleVenta==null)
        {
            return null;
        }

        return new DetalleVentaResponse(detalleVenta.getProducto().getId(),
                detalleVenta.getProducto().getNombre(),
                detalleVenta.getCantidadProducto(),
                detalleVenta.getPrecioProducto(),
                detalleVenta.getSubtotal());

    }


    public DetalleVenta requestAEntidad(
            DetalleVentaRequest request,
            Producto producto)
    {

        if (request == null)
        {
            return null;
        }

        return DetalleVenta.builder()
                .producto(producto)
                .cantidadProducto(request.cantidadProducto())
                .precioProducto(producto.getPrecio())
                .build();
    }



}

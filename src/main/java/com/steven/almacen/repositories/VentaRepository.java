package com.steven.almacen.repositories;

import com.steven.almacen.dto.reportes.ReporteVentasSucursalResponse;
import com.steven.almacen.entities.Venta;
import com.steven.almacen.enums.EstadoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {



   List<Venta>findByEstadoVenta(EstadoVenta estadoVenta);

   Optional<Venta> findByIdAndEstadoVenta(Long id, EstadoVenta estadoVenta);

   @Query("""
           select new com.steven.almacen.dto.reportes.ReporteVentasSucursalResponse(
               s.id,
               s.nombre,
               sum(d.precioProducto * d.cantidadProducto),
               sum(d.cantidadProducto)
           )
           from Venta v
           join v.sucursal s
           join v.detalleVentas d
           where v.estadoVenta = :estado
           group by s.id, s.nombre
           """)
   List<ReporteVentasSucursalResponse> reporteVentasPorSucursal(
           @Param("estado") EstadoVenta estado
   );

}

package com.steven.almacen.repositories;

import com.steven.almacen.entities.Venta;
import com.steven.almacen.enums.EstadoVenta;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {



   List<Venta>findByEstadoVenta(EstadoVenta estadoVenta);

   Optional<Venta> findByIdAndEstadoVenta(Long id, EstadoVenta estadoVenta);



}

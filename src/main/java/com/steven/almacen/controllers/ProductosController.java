package com.steven.almacen.controllers;


import com.steven.almacen.dto.productos.ProductosRequest;
import com.steven.almacen.dto.productos.ProductosResponse;
import com.steven.almacen.services.productos.ProductosService;
import com.steven.almacen.services.productos.ProductosServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/productos")
@AllArgsConstructor
@Validated
@Tag(name="Productos",description = "Endpoints para la gestion de productos")
public class ProductosController {

    private final ProductosService productosService;

    @GetMapping
    public ResponseEntity <List<ProductosResponse>> listar
            (@RequestParam (required = false) String nombre,
             @RequestParam (required = false) String categoria,
             @RequestParam (required = false)BigDecimal precioMin,
             @RequestParam (required = false) BigDecimal precioMax)
    {
      return  ResponseEntity.ok(productosService.listar(nombre,categoria,precioMin,precioMax));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proucto por id",tags = {"Productos - Consultas"})

    public ResponseEntity <ProductosResponse> obtenerId
            (@PathVariable @Positive(message = "El id debe de ser positivo") Long id)
    {
        return  ResponseEntity.ok(productosService.ObtenerPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar producto",tags = {"Productos - Gestion"})

    public ResponseEntity <ProductosResponse> registrar
            (@Valid @RequestBody ProductosRequest request)
    {
        return  ResponseEntity.status(201).body(productosService.registrar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar  producto",tags = {"Productos - Gestion"})

    public ResponseEntity <ProductosResponse> actualizar
            (@Valid @RequestBody ProductosRequest request, @PathVariable @Positive(message = "EL valor debe de ser positivo") Long id)
    {
        return  ResponseEntity.ok(productosService.actualizar(request,id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "ELiminar  producto",tags = {"Productos - Gestion"})

    public ResponseEntity<Void>   eliminar
            (@PathVariable @Positive(message = "EL valor debe de ser positivo") Long id)
    {
          productosService.eliminar(id);

          return ResponseEntity.noContent().build();
    }


}

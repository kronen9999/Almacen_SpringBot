package com.steven.almacen.controllers;


import com.steven.almacen.dto.sucursales.SucursalRequest;
import com.steven.almacen.dto.sucursales.SucursalResponse;
import com.steven.almacen.services.sucursales.SucursalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@AllArgsConstructor
@Validated
@Tag(name = "Sucursal",description = "Endpoints de sucursal")
public class SucursalController {

    private final SucursalService service;

    @GetMapping
    public ResponseEntity<List<SucursalResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity <SucursalResponse> obtenerPorId(@PathVariable @Positive(message = "El id debe ser positivo") Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<SucursalResponse> registrar(@RequestBody SucursalRequest request) {

        return ResponseEntity.ok(service.registrar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalResponse> actualizar(@RequestBody SucursalRequest request, @PathVariable @Positive Long id) {

        return ResponseEntity.ok(service.actualizar(request,id));


    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable @Positive Long id) {
        service.eliminar(id);

        ResponseEntity.noContent().build();
    }
}

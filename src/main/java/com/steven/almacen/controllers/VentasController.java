package com.steven.almacen.controllers;

import com.steven.almacen.dto.ventas.VentaRequest;
import com.steven.almacen.dto.ventas.VentaResponse;
import com.steven.almacen.services.ventas.VentasServiceImp;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@AllArgsConstructor
@Validated
@Tag(name = "Ventas",description = "Enpoints para realizar ventas de productos")
public class VentasController {

    private final VentasServiceImp ventasServiceImp;

    @PostMapping
    public ResponseEntity<VentaResponse> registrarProducto (@RequestBody VentaRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventasServiceImp.registrar(request ));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VentaResponse> cancelar (@PathVariable @Positive() Long id)
    {
        return  ResponseEntity.ok(ventasServiceImp.cancelar(id));
    }

    @GetMapping
    public ResponseEntity <List<VentaResponse>> listar ()
    {
        return ResponseEntity.status(HttpStatus.OK).body(ventasServiceImp.listar());
    }


    @GetMapping("/canceladas")
    public ResponseEntity <List<VentaResponse>> listarPorCancelada ()
    {
        return ResponseEntity.status(HttpStatus.OK).body(ventasServiceImp.listarCanceladas());
    }

    @GetMapping("/{id}")
    public ResponseEntity <VentaResponse> obtenerPorEstadoActiva (@PathVariable @Positive Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(ventasServiceImp.obtenerPorIdActiva(id));
    }
}

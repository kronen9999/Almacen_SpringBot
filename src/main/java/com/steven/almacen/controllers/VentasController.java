package com.steven.almacen.controllers;

import com.steven.almacen.dto.ventas.VentaRequest;
import com.steven.almacen.dto.ventas.VentaResponse;
import com.steven.almacen.services.ventas.VentasServiceImp;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
@AllArgsConstructor
@Tag(name = "Ventas",description = "Enpoints para realizar ventas de productos")
public class VentasController {

    private final VentasServiceImp ventasServiceImp;

    @PostMapping
    public ResponseEntity<VentaResponse> registrarProducto (@RequestBody VentaRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventasServiceImp.registrar(request ));
    }

}

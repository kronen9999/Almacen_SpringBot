package com.steven.almacen.utils;

import com.steven.almacen.dto.productos.ProductosRequest;
import com.steven.almacen.entities.Producto;
import com.steven.almacen.enums.Categoria;
import com.steven.almacen.repositories.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ProductosLoader implements CommandLineRunner {

    private  final ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {

        if (productoRepository.count()==0)
        {
            productoRepository.saveAll(List.of(
                    new Producto(null,"laptop Gamer", Categoria.ELECTRONICA, BigDecimal.valueOf(15000),10),
                    new Producto(null,"Mouse", Categoria.ELECTRONICA, BigDecimal.valueOf(300),459),
                    new Producto(null,"Monitor", Categoria.ELECTRONICA, BigDecimal.valueOf(2500),50)
            ));

            log.info("Metodo cargado");
        }


    }

}

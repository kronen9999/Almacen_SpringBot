package com.steven.almacen.repositories;

import com.steven.almacen.entities.Producto;
import com.steven.almacen.enums.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Long> {

    @Query ("select p from Producto  p where (:nombre is null  or :nombre='' or lower(p.nombre) LIKE  concat('%',lower(:nombre),'%' ) )" +
            "and (:categoria is null or p.categoria=:categoria) and (:minimo is null or p.precio>= :minimo) and (:maximo is null or p.precio<=:maximo)" )

    List<Producto> obtenerListaPersonalizada(
            @Param("nombre") String nombre,
            @Param("categoria") Categoria categoria,
            @Param("minimo") BigDecimal minimo,
            @Param ("maximo") BigDecimal maximo
    );

}

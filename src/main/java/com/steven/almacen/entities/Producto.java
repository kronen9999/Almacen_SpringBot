package com.steven.almacen.entities;

import com.steven.almacen.enums.Categoria;
import com.steven.almacen.utils.StringCustomUtils;
import com.steven.almacen.utils.ValoresNumericosUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="PRODUCTOS")
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
public class Producto {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_PRODUCTO")
    private Long id;
    @Column(name = "NOMBRE",length = 50,nullable = false)
    private String nombre;
    @Column(name = "CATEGORIA",nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Column(name = "PRECIO",nullable = false)
    private BigDecimal precio;
    @Column(name = "CANTIDAD",nullable = false)
    private Integer cantidad;

    public void aumentarCantidad (int cantidad)
    {
        ValoresNumericosUtils.validarEnteroPositivo(cantidad,"La cantidad debe de ser positiva ");


        this.cantidad+=cantidad;
    }

    public void descontarCantidad (int cantidad)
    {
        ValoresNumericosUtils.validarEnteroPositivo(cantidad,"La cantidad debe de ser positiva ");

        if (cantidad> this.cantidad)
        {
            throw new IllegalArgumentException("La cantidad debe de ser menor o igual que la cantidad actual");
        }

        this.cantidad-=cantidad;


        this.cantidad+=cantidad;
    }

    public void  validarDatos(String nombre, Categoria categoria, BigDecimal precio, Integer cantidad) {
        StringCustomUtils.validarTamanio(nombre,5,50,"El nombre es requerido debe de estar entre 5 y 30 caracteres");

        if (categoria==null)
        {
            throw  new IllegalArgumentException("La categoria es requerida");
        }

        ValoresNumericosUtils.validarBigDecimalPositivo(precio,"El precio es requerido y debe de ser positiva");

        ValoresNumericosUtils.validarEnteroPositivo(cantidad,"La cantidad es requerida y debe de ser positiva");
    }

    public void  actualizar(String nombre, Categoria categoria, BigDecimal precio, Integer cantidad) {

        validarDatos(nombre,categoria,precio,cantidad);

        this.nombre=nombre.trim();
        this.categoria=categoria;
        this.precio=precio;
        this.cantidad=cantidad;

    }


}

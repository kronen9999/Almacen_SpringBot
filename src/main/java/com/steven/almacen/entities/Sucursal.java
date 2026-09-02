package com.steven.almacen.entities;

import com.steven.almacen.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "SUCURSALES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder

public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUCURSAL")
    private Long id;

    @Column(nullable = false,name = "NOMBRE",length = 50,unique = true)
    private String nombre;

    @Column(nullable = false,name = "DIRECCION",length = 150)
    private String direccion;

    public void validarDatos(String nombre, String direccion) {
        StringCustomUtils.validarTamanio(nombre,5,50,"El nombre debe tener entre 5 y 50 caracteres");

        StringCustomUtils.validarTamanio(direccion,5,150,"El nombre debe tener entre 5 y 150 caracteres");
    }

    public void actualizar(String nombre, String direccion) {
        validarDatos(nombre,direccion);
        this.nombre=nombre;
        this.direccion=direccion;
    }
}

package com.steven.almacen.entities;


import com.steven.almacen.enums.EstadoVenta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "VENTAS")

public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "ID_VENTA")
    private Long id;

    @Column(name = "ESTADO",nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoVenta estadoVenta;

    @Column(name = "FECHA",nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUCURSAL",nullable = false)
    private Sucursal sucursal;

    @OneToMany (fetch = FetchType.LAZY,mappedBy = "venta",cascade = CascadeType.ALL)
    @Builder.Default
    private List<DetalleVenta> detalleVentas = new ArrayList<>();

    public void agregarDetalle (DetalleVenta detalleVenta)
    {
        if (detalleVenta ==null)
        {
            this.detalleVentas=new ArrayList<>();
        }

        this.detalleVentas.add(detalleVenta);
        detalleVenta.asignarVenta(this);
    }

    public void cancelar ()
    {

        log.info("Cancelando venta");
        if (estadoVenta==EstadoVenta.CANCELADA)


            throw  new IllegalArgumentException( "La venta ya esta cancelada");

        estadoVenta=EstadoVenta.CANCELADA;
    }

    public BigDecimal getTotal()
    {
        log.info("Calculando precio total");
        return detalleVentas.stream().map(detalleVenta -> detalleVenta.getPrecioProducto().multiply(BigDecimal.valueOf(detalleVenta.getCantidadProducto()))).reduce(BigDecimal.ZERO,BigDecimal::add);



    }

}

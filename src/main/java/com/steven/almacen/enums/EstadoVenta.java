package com.steven.almacen.enums;

import com.steven.almacen.exceptions.RecursoNoEncontradoException;
import com.steven.almacen.utils.StringCustomUtils;
import com.steven.almacen.utils.ValoresNumericosUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter

public enum EstadoVenta {


    REGISTRADA(1L,"Registrada"),
    CANCELADA(0L,"Cancelada");

    private final Long codigo;

    private final String descripcion;

    public static EstadoVenta obtenerEstadoVentaPorDescripcion(String descripcion)
    {
        StringCustomUtils.validarNoVacio(descripcion,"La descripcion es requerida");

        String descripcionNormalizada =StringCustomUtils.quitarAcentos(descripcion);




        for (EstadoVenta estadoVenta:values())
        {
            if (StringCustomUtils.quitarAcentos(estadoVenta.descripcion).equalsIgnoreCase(descripcionNormalizada))

            {
                return  estadoVenta;
            }
        }

        throw  new RecursoNoEncontradoException("No existe una categoria para la descripcion : "+descripcion );


    }
    public static EstadoVenta obtenerEstadoVentaPorCodigo(Long codigo)
    {
        ValoresNumericosUtils.validarNumeroRequerido(codigo);





        for (EstadoVenta estadoVenta:values())
        {
            if (estadoVenta.codigo.equals(codigo))

            {
                return  estadoVenta;
            }
        }

        throw  new RecursoNoEncontradoException("No existe una categoria para el codigo : "+codigo );


    }
}

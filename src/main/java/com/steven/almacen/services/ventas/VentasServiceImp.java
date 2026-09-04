package com.steven.almacen.services.ventas;


import com.steven.almacen.dto.ventas.VentaRequest;
import com.steven.almacen.dto.ventas.VentaResponse;
import com.steven.almacen.entities.DetalleVenta;
import com.steven.almacen.entities.Producto;
import com.steven.almacen.entities.Sucursal;
import com.steven.almacen.entities.Venta;
import com.steven.almacen.enums.EstadoVenta;
import com.steven.almacen.exceptions.RecursoNoEncontradoException;
import com.steven.almacen.mappers.DetalleMapper;
import com.steven.almacen.mappers.VentaMapper;
import com.steven.almacen.repositories.ProductoRepository;
import com.steven.almacen.repositories.SucursalRepository;
import com.steven.almacen.repositories.VentaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class VentasServiceImp implements VentasService{

    private final SucursalRepository sucursalRepository;
    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private  final ProductoRepository productoRepository;
    private final DetalleMapper detalleMapper;



    @Transactional(readOnly = true)
    @Override
    public List<VentaResponse> listar() {
        return  listarPorEstado(EstadoVenta.REGISTRADA);

    }
    @Transactional(readOnly = true)
    @Override
    public List<VentaResponse> listarCanceladas() {

        return listarPorEstado(EstadoVenta.CANCELADA);

    }

    @Transactional(readOnly = true)
    @Override
    public VentaResponse obtenerPorIdActiva(Long id) {

         return ventaMapper.entidadAResponse(ventaRepository.findByIdAndEstadoVenta(id,EstadoVenta.REGISTRADA).orElseThrow(
                 ()-> new RecursoNoEncontradoException("No se ha encontrado la venta con id :"+ id)
         ));

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public VentaResponse registrar(VentaRequest request) {

        Sucursal sucursal = buscarSucursalExistentePorId(request.idSucursal());

        List<DetalleVenta> detalles = new ArrayList<>();

        request.productos().forEach(detalleVentaRequest -> {


            Producto producto = buscarProductoPorId(detalleVentaRequest.idProducto());

            producto.descontarCantidad(detalleVentaRequest.cantidadProducto());

            DetalleVenta detalle =detalleMapper.requestAEntidad(detalleVentaRequest,producto);

            detalles.add(detalle);


        });



          Venta venta=  ventaRepository.save(ventaMapper.requestAEntidad(EstadoVenta.REGISTRADA, LocalDate.now(),sucursal,detalles));

         return ventaMapper.entidadAResponse(venta);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public VentaResponse cancelar(Long id) {

        Venta venta= buscarVentaPorId(id);

        venta.cancelar();

        List <DetalleVenta> detalleVentas=venta.getDetalleVentas();

        detalleVentas.forEach(detalleVenta -> {

            Producto producto = buscarProductoPorId(detalleVenta.getProducto().getId());

            producto.aumentarCantidad(detalleVenta.getCantidadProducto());

        });

        ventaRepository.save(venta);

        return ventaMapper.entidadAResponse(venta);

    }

    private Sucursal buscarSucursalExistentePorId(Long id )
    {
        log.info("Buscando sucursal con id : {}",id);

       return  sucursalRepository.findById(id).orElseThrow(
                () -> new RecursoNoEncontradoException("No se encontro la sucursal con el id proporcionado : "+id )
        );
    }


    private Producto buscarProductoPorId(Long id)
    {

        log.info("Buscnando producto con el id : {}",id);

        return productoRepository.findById(
                id).orElseThrow(
                ()-> new RecursoNoEncontradoException("No se encontro el producto con id "+id
                )
        );

    }

    private Venta buscarVentaPorId(Long id)
    {
        log.info("Buscando venta");

        return ventaRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No se ha encontrado la venta con id : "+id));

    }

    private List <VentaResponse> listarPorEstado(EstadoVenta estadoVenta)
    {

        log.info("Buscando ventas por estado : {}",estadoVenta.toString());

        List <Venta> venta = ventaRepository.findByEstadoVenta(estadoVenta);

        return venta.stream().map(ventaMapper::entidadAResponse).toList();


    }
}

package com.steven.almacen.services.productos;

import com.steven.almacen.dto.productos.ProductosRequest;
import com.steven.almacen.dto.productos.ProductosResponse;
import com.steven.almacen.entities.Producto;
import com.steven.almacen.enums.Categoria;
import com.steven.almacen.exceptions.ConflictoMayorMenor;
import com.steven.almacen.exceptions.RecursoNoEncontradoException;
import com.steven.almacen.mappers.ProductoMapper;
import com.steven.almacen.repositories.ProductoRepository;
import com.steven.almacen.utils.StringCustomUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ProductosServiceImp implements ProductosService{

    private  final ProductoMapper productoMapper;
    private final ProductoRepository productoRepository;


    @Transactional(readOnly = true)
    @Override
    public List<ProductosResponse> listar(String nombre, String categoria, BigDecimal precioMin, BigDecimal precioMax) {
        log.info("Listando todos los productos ");

        Categoria categoriaEnum = categoria == null || categoria.isBlank()?null:Categoria.obtenerCategoriaPorDescripcion(categoria);


            validarMenorMayor(precioMin,precioMax);



        return productoRepository.obtenerListaPersonalizada(nombre,categoriaEnum,precioMin,precioMax).stream().map(productoMapper::entidadAResponse).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ProductosResponse ObtenerPorId(Long id) {
        log.info("Obteniendo producto por id " + id);
        return productoMapper.entidadAResponse(obtenerProductoOException(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProductosResponse registrar(ProductosRequest request) {
        Producto producto = productoMapper.requestAEntidad(request, Categoria.obtenerCategoriaPorDescripcion(request.categoria()));

        productoRepository.save(producto);

        log.info("Nuevo producto {} registrado ",producto.getNombre());

        return productoMapper.entidadAResponse(producto);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProductosResponse actualizar(ProductosRequest request, Long id) {

        Producto producto = obtenerProductoOException(id);

        log.info("Actualizando producto con id {}",id);

       producto.actualizar(request.Nombre(),
               Categoria.obtenerCategoriaPorDescripcion(request.categoria()),
               request.precio(),
               request.cantidad());

       log.info("Producto con id {} actualizado id ",id);

       return  productoMapper.entidadAResponse(producto);

    }

    @Override
    public void eliminar(Long id) {

        Producto producto = obtenerProductoOException(id);

        log.info("Eliminando producto con id {}",id);

        productoRepository.delete(producto);

    }

    private Producto obtenerProductoOException(Long id)
    {
        log.info("Obteniendo producto");

       return  productoRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Id no encontrado con id "+id));
    }

    private void validarMenorMayor (BigDecimal precioMin, BigDecimal precioMax)
    {

        log.info("Verificando que el precio minimo no sea mayor al precio maximo");
        if (precioMin==null||precioMax==null) return;

        if (precioMin.compareTo(precioMax)==1)
        {
            log.error("Se produjo un error el valor para el min es {} y para el max es {}",precioMin,precioMax);
            throw  new ConflictoMayorMenor("El numero menor no puede ser mayor al numero menor");

        }

        log.info("validacion correcta");


    }


}

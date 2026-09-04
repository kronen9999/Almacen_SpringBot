package com.steven.almacen.services.sucursales;

import com.steven.almacen.dto.sucursales.SucursalRequest;
import com.steven.almacen.dto.sucursales.SucursalResponse;
import com.steven.almacen.entities.Sucursal;
import com.steven.almacen.exceptions.RecursoNoEncontradoException;
import com.steven.almacen.mappers.SucursalMapper;
import com.steven.almacen.repositories.SucursalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SucursalesServicioImp implements  SucursalService{
    private final SucursalRepository repository;

    private final SucursalMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<SucursalResponse> listar() {
        log.info("Listando las sucursales");
        return repository.findAll().stream().map(mapper::entidadAResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SucursalResponse obtenerPorId(Long id) {

        log.info("Obteninedo la sucursal por el id : {}",id);
        return mapper.entidadAResponse(buscarPorId(id));

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SucursalResponse registrar(SucursalRequest request) {
        log.info("Registrando una nueva sucursal");
        Sucursal sucursal = mapper.requestAEntidad(request);
        validarDatosUnicos(request);
        repository.save(sucursal);

        return mapper.entidadAResponse(sucursal);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SucursalResponse actualizar(SucursalRequest request, Long id) {
        Sucursal sucursal=obtenerSucursalException(id);
        log.info("Actualizando sucursal con id:{}",id);
        validarCambiosUnicos(request,id);
        sucursal.actualizar(request.nombre(),request.direccion());
        log.info("Sucursal con i {} actualizada",id);
        return mapper.entidadAResponse(sucursal);
    }

    @Override
    public void eliminar(Long id) {

        log.info("Eliminando sucursal con id : {}",id);
    Sucursal sucursal =buscarPorId(id);

    repository.delete(sucursal);
    }

    private Sucursal buscarPorId(Long id)
    {

        return  repository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No se encontro la sucursal"));

    }

    private void validarDatosUnicos(SucursalRequest request){
        log.info("validando nombre unico....");
        if (repository.existsByNombreIgnoreCase(request.nombre().trim()))
            throw new IllegalArgumentException("Ya existe una sucursal con el nombre :"+request.nombre());
    }

    private void validarCambiosUnicos(SucursalRequest request, Long id){
        log.info("validando cambio nombre unico....");
        if (repository.existsByNombreIgnoreCaseAndIdNot(request.nombre().trim(),id))
            throw new IllegalArgumentException("Ya existe una sucursal con el nombre :"+request.nombre());
    }

    private Sucursal obtenerSucursalException(Long id){
        log.info("Obteniendo la sucursal por el id :  {}",id);
        return repository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No se encontro sucursal"));

    }
}

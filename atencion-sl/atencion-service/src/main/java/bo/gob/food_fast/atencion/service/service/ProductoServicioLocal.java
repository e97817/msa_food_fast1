package bo.gob.food_fast.atencion.service.service;

import java.util.List;

import bo.gob.food_fast.atencion.dto.dto.ProductoDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;

public interface ProductoServicioLocal {

    RespuestaBaseResponse buscarPorId(Long id);

    RespuestaBaseResponse listar();

    RespuestaBaseResponse listarDisponibles();

    RespuestaBaseResponse guardar(ProductoDTO productoDTO);

    RespuestaBaseResponse actualizar(ProductoDTO productoDTO);

    RespuestaBaseResponse eliminar(Long id);
}
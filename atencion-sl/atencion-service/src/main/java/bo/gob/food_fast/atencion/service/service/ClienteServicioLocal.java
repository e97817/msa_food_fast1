package bo.gob.food_fast.atencion.service.service;

import java.util.List;

import bo.gob.food_fast.atencion.dto.dto.ClienteDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;

public interface ClienteServicioLocal {

    RespuestaBaseResponse buscarPorId(Long id);

    RespuestaBaseResponse listar();

    RespuestaBaseResponse guardar(ClienteDTO clienteDTO);

    RespuestaBaseResponse actualizar(ClienteDTO clienteDTO);

    RespuestaBaseResponse eliminar(Long id);
}
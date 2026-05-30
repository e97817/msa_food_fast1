package bo.gob.food_fast.atencion.service.service;

import java.util.List;

import bo.gob.food_fast.atencion.dto.dto.MesaDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;

public interface MesaServicioLocal {

    RespuestaBaseResponse buscarPorId(Long id);

    RespuestaBaseResponse listar();

    RespuestaBaseResponse listarPorEstado(String estado);

    RespuestaBaseResponse guardar(MesaDTO mesaDTO);

    RespuestaBaseResponse actualizar(MesaDTO mesaDTO);

    RespuestaBaseResponse eliminar(Long id);
}
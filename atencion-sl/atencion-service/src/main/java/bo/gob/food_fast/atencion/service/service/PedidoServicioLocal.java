package bo.gob.food_fast.atencion.service.service;

import java.util.List;

import bo.gob.food_fast.atencion.dto.dto.PedidoDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;

public interface PedidoServicioLocal {

    RespuestaBaseResponse buscarPorId(Long id);

    RespuestaBaseResponse listar();

    RespuestaBaseResponse listarPorCliente(Long clienteId);

    RespuestaBaseResponse guardar(PedidoDTO pedidoDTO);

    RespuestaBaseResponse actualizar(PedidoDTO pedidoDTO);

    RespuestaBaseResponse eliminar(Long id);
}
package bo.gob.food_fast.atencion.service.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import bo.gob.food_fast.atencion.dto.dto.DetallePedidoDTO;
import bo.gob.food_fast.atencion.dto.dto.PedidoDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import bo.gob.food_fast.atencion.entity.entities.ClienteJPA;
import bo.gob.food_fast.atencion.entity.entities.DetallePedidoJPA;
import bo.gob.food_fast.atencion.entity.entities.PedidoJPA;
import bo.gob.food_fast.atencion.repository.repository.ClienteDao;
import bo.gob.food_fast.atencion.repository.repository.PedidoDao;

@RequestScoped
public class PedidoServicio implements PedidoServicioLocal {

    @Inject
    PedidoDao pedidoDao;

    @Inject
    ClienteDao clienteDao;

    private ModelMapper vModelMapper;

    @Inject
    public PedidoServicio(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
        this.vModelMapper = new ModelMapper();
        this.vModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public RespuestaBaseResponse buscarPorId(Long id) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            PedidoJPA vResultadoEntidad = pedidoDao.buscarPorId(id);
            if (vResultadoEntidad != null) {
                PedidoDTO vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<PedidoDTO>() {
                }.getType());
                vRespuesta.success = true;
                vRespuesta.mensajes = List.of("Pedido encontrado correctamente");
                vRespuesta.respuesta = vResultado;
            } else {
                vRespuesta.success = false;
                vRespuesta.mensajes = List.of("Pedido no encontrado");
                vRespuesta.respuesta = null;
            }
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al buscar pedido: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse listar() {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            List<PedidoJPA> vResultadoEntidad = pedidoDao.listar();
            List<PedidoDTO> vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<List<PedidoDTO>>() {
            }.getType());
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Pedidos encontrados correctamente");
            vRespuesta.respuesta = vResultado;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al listar pedidos: " + e.getMessage());
            vRespuesta.respuesta = new ArrayList<>();
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse listarPorCliente(Long clienteId) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            List<PedidoJPA> vResultadoEntidad = pedidoDao.listarPorCliente(clienteId);
            List<PedidoDTO> vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<List<PedidoDTO>>() {
            }.getType());
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Pedidos del cliente encontrados correctamente");
            vRespuesta.respuesta = vResultado;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al listar pedidos del cliente: " + e.getMessage());
            vRespuesta.respuesta = new ArrayList<>();
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse guardar(PedidoDTO pedidoDTO) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            pedidoDTO.id = null;
            pedidoDTO.fechaPedido = new Date();
            if (pedidoDTO.estado == null) {
                pedidoDTO.estado = "PENDIENTE";
            }
            if (pedidoDTO.total == null) {
                pedidoDTO.total = BigDecimal.ZERO;
            }
            ClienteJPA cliente = clienteDao.buscarPorId(pedidoDTO.clienteId);
            if (cliente == null) {
                vRespuesta.success = false;
                vRespuesta.mensajes = List.of("Cliente no encontrado");
                vRespuesta.respuesta = null;
                return vRespuesta;
            }
            PedidoJPA pedido = new PedidoJPA();
            pedido.cliente = cliente;
            pedido.clienteId = cliente.id;
            pedido.fechaPedido = pedidoDTO.fechaPedido;
            pedido.estado = pedidoDTO.estado;
            pedido.total = pedidoDTO.total;
            List<DetallePedidoJPA> detalles = new ArrayList<>();
            if (pedidoDTO.detallesPedido != null && !pedidoDTO.detallesPedido.isEmpty()) {
                for (DetallePedidoDTO detDTO : pedidoDTO.detallesPedido) {
                    DetallePedidoJPA det = new DetallePedidoJPA();
                    det.pedido = pedido;
                    det.productoId = detDTO.productoId;
                    det.cantidad = detDTO.cantidad;
                    det.precioUnitario = detDTO.precioUnitario;
                    det.subtotal = detDTO.subtotal;
                    detalles.add(det);
                }
            }
            pedido.detallesPedido = detalles;
            pedidoDao.guardar(pedido);
            vModelMapper.map(pedido, pedidoDTO);
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Pedido guardado correctamente");
            vRespuesta.respuesta = pedidoDTO;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al guardar pedido: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse actualizar(PedidoDTO pedidoDTO) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            PedidoJPA pedido = pedidoDao.buscarPorId(pedidoDTO.id);
            if (pedido != null) {
                if (pedidoDTO.clienteId != null && !pedidoDTO.clienteId.equals(pedido.clienteId)) {
                    ClienteJPA cliente = clienteDao.buscarPorId(pedidoDTO.clienteId);
                    if (cliente == null) {
                        vRespuesta.success = false;
                        vRespuesta.mensajes = List.of("Cliente no encontrado");
                        vRespuesta.respuesta = null;
                        return vRespuesta;
                    }
                    pedido.cliente = cliente;
                    pedido.clienteId = cliente.id;
                }
                if (pedidoDTO.estado != null) {
                    pedido.estado = pedidoDTO.estado;
                }
                if (pedidoDTO.total != null) {
                    pedido.total = pedidoDTO.total;
                }
                pedidoDao.actualizar(pedido);
                vModelMapper.map(pedido, pedidoDTO);
                vRespuesta.success = true;
                vRespuesta.mensajes = List.of("Pedido actualizado correctamente");
                vRespuesta.respuesta = pedidoDTO;
            } else {
                vRespuesta.success = false;
                vRespuesta.mensajes = List.of("Pedido no encontrado");
                vRespuesta.respuesta = null;
            }
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al actualizar pedido: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse eliminar(Long id) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            pedidoDao.eliminar(id);
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Pedido eliminado correctamente");
            vRespuesta.respuesta = null;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al eliminar pedido: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }
}
package bo.gob.food_fast.atencion.service.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import bo.gob.food_fast.atencion.dto.dto.ClienteDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import bo.gob.food_fast.atencion.entity.entities.ClienteJPA;
import bo.gob.food_fast.atencion.repository.repository.ClienteDao;

@RequestScoped
public class ClienteServicio implements ClienteServicioLocal {

    @Inject
    ClienteDao clienteDao;

    private ModelMapper vModelMapper;

    @Inject
    public ClienteServicio(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
        this.vModelMapper = new ModelMapper();
        this.vModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public RespuestaBaseResponse buscarPorId(Long id) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            ClienteJPA vResultadoEntidad = clienteDao.buscarPorId(id);
            if (vResultadoEntidad != null) {
                ClienteDTO vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<ClienteDTO>() {
                }.getType());
                vRespuesta.success = true;
                vRespuesta.mensajes = List.of("Cliente encontrado correctamente");
                vRespuesta.respuesta = vResultado;
            } else {
                vRespuesta.success = false;
                vRespuesta.mensajes = List.of("Cliente no encontrado");
                vRespuesta.respuesta = null;
            }
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al buscar cliente: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse listar() {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            List<ClienteJPA> vResultadoEntidad = clienteDao.listar();
            List<ClienteDTO> vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<List<ClienteDTO>>() {
            }.getType());
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Clientes encontrados correctamente");
            vRespuesta.respuesta = vResultado;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al listar clientes: " + e.getMessage());
            vRespuesta.respuesta = new ArrayList<>();
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse guardar(ClienteDTO clienteDTO) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            clienteDTO.id = null;
            clienteDTO.fechaRegistro = new Date();
            ClienteJPA vResultado = vModelMapper.map(clienteDTO, new TypeToken<ClienteJPA>() {
            }.getType());
            clienteDao.guardar(vResultado);
            vModelMapper.map(vResultado, clienteDTO);
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Cliente guardado correctamente");
            vRespuesta.respuesta = clienteDTO;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al guardar cliente: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse actualizar(ClienteDTO clienteDTO) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            ClienteJPA vResultado = clienteDao.buscarPorId(clienteDTO.id);
            if (vResultado != null) {
                vModelMapper.map(clienteDTO, vResultado);
                clienteDao.actualizar(vResultado);
                vModelMapper.map(vResultado, clienteDTO);
                vRespuesta.success = true;
                vRespuesta.mensajes = List.of("Cliente actualizado correctamente");
                vRespuesta.respuesta = clienteDTO;
            } else {
                vRespuesta.success = false;
                vRespuesta.mensajes = List.of("Cliente no encontrado");
                vRespuesta.respuesta = null;
            }
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al actualizar cliente: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse eliminar(Long id) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            clienteDao.eliminar(id);
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Cliente eliminado correctamente");
            vRespuesta.respuesta = null;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al eliminar cliente: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }
}
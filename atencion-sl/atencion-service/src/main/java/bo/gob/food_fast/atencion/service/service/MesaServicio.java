package bo.gob.food_fast.atencion.service.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import bo.gob.food_fast.atencion.dto.dto.MesaDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import bo.gob.food_fast.atencion.entity.entities.MesaJPA;
import bo.gob.food_fast.atencion.repository.repository.MesaDao;

@RequestScoped
public class MesaServicio implements MesaServicioLocal {

    @Inject
    MesaDao mesaDao;

    private ModelMapper vModelMapper;

    @Inject
    public MesaServicio(MesaDao mesaDao) {
        this.mesaDao = mesaDao;
        this.vModelMapper = new ModelMapper();
        this.vModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public RespuestaBaseResponse buscarPorId(Long id) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            MesaJPA vResultadoEntidad = mesaDao.buscarPorId(id);
            if (vResultadoEntidad != null) {
                MesaDTO vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<MesaDTO>() {
                }.getType());
                vRespuesta.success = true;
                vRespuesta.mensajes = List.of("Mesa encontrada correctamente");
                vRespuesta.respuesta = vResultado;
            } else {
                vRespuesta.success = false;
                vRespuesta.mensajes = List.of("Mesa no encontrada");
                vRespuesta.respuesta = null;
            }
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al buscar mesa: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse listar() {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            List<MesaJPA> vResultadoEntidad = mesaDao.listar();
            List<MesaDTO> vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<List<MesaDTO>>() {
            }.getType());
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Mesas encontradas correctamente");
            vRespuesta.respuesta = vResultado;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al listar mesas: " + e.getMessage());
            vRespuesta.respuesta = new ArrayList<>();
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse listarPorEstado(String estado) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            List<MesaJPA> vResultadoEntidad = mesaDao.listarPorEstado(estado);
            List<MesaDTO> vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<List<MesaDTO>>() {
            }.getType());
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Mesas por estado encontradas correctamente");
            vRespuesta.respuesta = vResultado;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al listar mesas por estado: " + e.getMessage());
            vRespuesta.respuesta = new ArrayList<>();
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse guardar(MesaDTO mesaDTO) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            mesaDTO.id = null;
            if (mesaDTO.estado == null) {
                mesaDTO.estado = "LIBRE";
            }
            MesaJPA vResultado = vModelMapper.map(mesaDTO, new TypeToken<MesaJPA>() {
            }.getType());
            mesaDao.guardar(vResultado);
            vModelMapper.map(vResultado, mesaDTO);
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Mesa guardada correctamente");
            vRespuesta.respuesta = mesaDTO;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al guardar mesa: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse actualizar(MesaDTO mesaDTO) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            MesaJPA vResultado = mesaDao.buscarPorId(mesaDTO.id);
            if (vResultado != null) {
                vModelMapper.map(mesaDTO, vResultado);
                mesaDao.actualizar(vResultado);
                vModelMapper.map(vResultado, mesaDTO);
                vRespuesta.success = true;
                vRespuesta.mensajes = List.of("Mesa actualizada correctamente");
                vRespuesta.respuesta = mesaDTO;
            } else {
                vRespuesta.success = false;
                vRespuesta.mensajes = List.of("Mesa no encontrada");
                vRespuesta.respuesta = null;
            }
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al actualizar mesa: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse eliminar(Long id) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            mesaDao.eliminar(id);
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Mesa eliminada correctamente");
            vRespuesta.respuesta = null;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al eliminar mesa: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }
}
package bo.gob.food_fast.atencion.service.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;

import bo.gob.food_fast.atencion.dto.dto.ProductoDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import bo.gob.food_fast.atencion.entity.entities.ProductoJPA;
import bo.gob.food_fast.atencion.repository.repository.ProductoDao;

@RequestScoped
public class ProductoServicio implements ProductoServicioLocal {

    @Inject
    ProductoDao productoDao;

    private ModelMapper vModelMapper;

    @Inject
    public ProductoServicio(ProductoDao productoDao) {
        this.productoDao = productoDao;
        this.vModelMapper = new ModelMapper();
        this.vModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public RespuestaBaseResponse buscarPorId(Long id) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            ProductoJPA vResultadoEntidad = productoDao.buscarPorId(id);
            if (vResultadoEntidad != null) {
                ProductoDTO vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<ProductoDTO>() {
                }.getType());
                vRespuesta.success = true;
                vRespuesta.mensajes = List.of("Producto encontrado correctamente");
                vRespuesta.respuesta = vResultado;
            } else {
                vRespuesta.success = false;
                vRespuesta.mensajes = List.of("Producto no encontrado");
                vRespuesta.respuesta = null;
            }
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al buscar producto: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse listar() {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            List<ProductoJPA> vResultadoEntidad = productoDao.listar();
            List<ProductoDTO> vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<List<ProductoDTO>>() {
            }.getType());
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Productos encontrados correctamente");
            vRespuesta.respuesta = vResultado;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al listar productos: " + e.getMessage());
            vRespuesta.respuesta = new ArrayList<>();
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse listarDisponibles() {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            List<ProductoJPA> vResultadoEntidad = productoDao.listarDisponibles();
            List<ProductoDTO> vResultado = vModelMapper.map(vResultadoEntidad, new TypeToken<List<ProductoDTO>>() {
            }.getType());
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Productos disponibles encontrados correctamente");
            vRespuesta.respuesta = vResultado;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al listar productos disponibles: " + e.getMessage());
            vRespuesta.respuesta = new ArrayList<>();
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse guardar(ProductoDTO productoDTO) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            productoDTO.id = null;
            if (productoDTO.disponible == null) {
                productoDTO.disponible = true;
            }
            ProductoJPA vResultado = vModelMapper.map(productoDTO, new TypeToken<ProductoJPA>() {
            }.getType());
            productoDao.guardar(vResultado);
            vModelMapper.map(vResultado, productoDTO);
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Producto guardado correctamente");
            vRespuesta.respuesta = productoDTO;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al guardar producto: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse actualizar(ProductoDTO productoDTO) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            ProductoJPA vResultado = productoDao.buscarPorId(productoDTO.id);
            if (vResultado != null) {
                vModelMapper.map(productoDTO, vResultado);
                productoDao.actualizar(vResultado);
                vModelMapper.map(vResultado, productoDTO);
                vRespuesta.success = true;
                vRespuesta.mensajes = List.of("Producto actualizado correctamente");
                vRespuesta.respuesta = productoDTO;
            } else {
                vRespuesta.success = false;
                vRespuesta.mensajes = List.of("Producto no encontrado");
                vRespuesta.respuesta = null;
            }
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al actualizar producto: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }

    @Override
    public RespuestaBaseResponse eliminar(Long id) {
        RespuestaBaseResponse vRespuesta = new RespuestaBaseResponse();
        try {
            productoDao.eliminar(id);
            vRespuesta.success = true;
            vRespuesta.mensajes = List.of("Producto eliminado correctamente");
            vRespuesta.respuesta = null;
        } catch (Exception e) {
            vRespuesta.success = false;
            vRespuesta.mensajes = List.of("Error al eliminar producto: " + e.getMessage());
            vRespuesta.respuesta = null;
        }
        return vRespuesta;
    }
}
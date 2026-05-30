package bo.gob.food_fast.atencion.controller.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import bo.gob.food_fast.atencion.dto.dto.ProductoDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import bo.gob.food_fast.atencion.service.service.ProductoServicio;
import bo.gob.food_fast.shared.constant.Constant;

@Path(Constant.BASE_CONTEXT + "/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = Constant.TAG_2_2)
public class ProductoController {

    @Inject
    ProductoServicio productoServicio;

    @GET
    @Operation(summary = "Obtener todos los productos", description = "Devuelve la lista de productos")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response listar() {
        RespuestaBaseResponse vResponse = productoServicio.listar();
        return Response.ok(vResponse).build();
    }

    @GET
    @Path("/disponibles")
    @Operation(summary = "Obtener productos disponibles", description = "Devuelve la lista de productos disponibles")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response listarDisponibles() {
        RespuestaBaseResponse vResponse = productoServicio.listarDisponibles();
        return Response.ok(vResponse).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Devuelve un producto según su ID")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response buscarPorId(@PathParam("id") Long id) {
        RespuestaBaseResponse vResponse = productoServicio.buscarPorId(id);
        return Response.ok(vResponse).build();
    }

    @POST
    @Operation(summary = "Guardar producto", description = "Guarda un nuevo producto")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response guardar(ProductoDTO productoDTO) {
        RespuestaBaseResponse vResponse = productoServicio.guardar(productoDTO);
        return Response.ok(vResponse).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response actualizar(@PathParam("id") Long id, ProductoDTO productoDTO) {
        productoDTO.id = id;
        RespuestaBaseResponse vResponse = productoServicio.actualizar(productoDTO);
        return Response.ok(vResponse).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por su ID")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response eliminar(@PathParam("id") Long id) {
        RespuestaBaseResponse vResponse = productoServicio.eliminar(id);
        return Response.ok(vResponse).build();
    }
}
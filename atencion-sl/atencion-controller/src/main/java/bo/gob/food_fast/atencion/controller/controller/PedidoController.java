package bo.gob.food_fast.atencion.controller.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import bo.gob.food_fast.atencion.dto.dto.PedidoDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import bo.gob.food_fast.atencion.service.service.PedidoServicio;
import bo.gob.food_fast.shared.constant.Constant;

@Path(Constant.BASE_CONTEXT + "/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = Constant.TAG_2_3)
public class PedidoController {

    @Inject
    PedidoServicio pedidoServicio;

    @GET
    @Operation(summary = "Obtener todos los pedidos", description = "Devuelve la lista de pedidos")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response listar() {
        RespuestaBaseResponse vResponse = pedidoServicio.listar();
        return Response.ok(vResponse).build();
    }

    @GET
    @Path("/cliente/{clienteId}")
    @Operation(summary = "Obtener pedidos por cliente", description = "Devuelve los pedidos de un cliente")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response listarPorCliente(@PathParam("clienteId") Long clienteId) {
        RespuestaBaseResponse vResponse = pedidoServicio.listarPorCliente(clienteId);
        return Response.ok(vResponse).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Obtener pedido por ID", description = "Devuelve un pedido según su ID")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response buscarPorId(@PathParam("id") Long id) {
        RespuestaBaseResponse vResponse = pedidoServicio.buscarPorId(id);
        return Response.ok(vResponse).build();
    }

    @POST
    @Operation(summary = "Guardar pedido", description = "Guarda un nuevo pedido")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response guardar(PedidoDTO pedidoDTO) {
        RespuestaBaseResponse vResponse = pedidoServicio.guardar(pedidoDTO);
        return Response.ok(vResponse).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar pedido", description = "Actualiza un pedido existente")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response actualizar(@PathParam("id") Long id, PedidoDTO pedidoDTO) {
        pedidoDTO.id = id;
        RespuestaBaseResponse vResponse = pedidoServicio.actualizar(pedidoDTO);
        return Response.ok(vResponse).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar pedido", description = "Elimina un pedido por su ID")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response eliminar(@PathParam("id") Long id) {
        RespuestaBaseResponse vResponse = pedidoServicio.eliminar(id);
        return Response.ok(vResponse).build();
    }
}
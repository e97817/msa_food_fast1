package bo.gob.food_fast.atencion.controller.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import bo.gob.food_fast.atencion.dto.dto.ClienteDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import bo.gob.food_fast.atencion.service.service.ClienteServicio;
import bo.gob.food_fast.shared.constant.Constant;

@Path(Constant.BASE_CONTEXT + "/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = Constant.TAG_2_1)
public class ClienteController {

    @Inject
    ClienteServicio clienteServicio;

    @GET
    @Operation(summary = "Obtener todos los clientes", description = "Devuelve la lista de clientes")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response listar() {
        RespuestaBaseResponse vResponse = clienteServicio.listar();
        return Response.ok(vResponse).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Devuelve un cliente según su ID")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response buscarPorId(@PathParam("id") Long id) {
        RespuestaBaseResponse vResponse = clienteServicio.buscarPorId(id);
        return Response.ok(vResponse).build();
    }

    @POST
    @Operation(summary = "Guardar cliente", description = "Guarda un nuevo cliente")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response guardar(ClienteDTO clienteDTO) {
        RespuestaBaseResponse vResponse = clienteServicio.guardar(clienteDTO);
        return Response.ok(vResponse).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Actualiza un cliente existente")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response actualizar(@PathParam("id") Long id, ClienteDTO clienteDTO) {
        clienteDTO.id = id;
        RespuestaBaseResponse vResponse = clienteServicio.actualizar(clienteDTO);
        return Response.ok(vResponse).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente por su ID")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response eliminar(@PathParam("id") Long id) {
        RespuestaBaseResponse vResponse = clienteServicio.eliminar(id);
        return Response.ok(vResponse).build();
    }
}
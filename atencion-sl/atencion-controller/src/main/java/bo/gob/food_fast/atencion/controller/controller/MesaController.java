package bo.gob.food_fast.atencion.controller.controller;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import bo.gob.food_fast.atencion.dto.dto.MesaDTO;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import bo.gob.food_fast.atencion.service.service.MesaServicio;
import bo.gob.food_fast.shared.constant.Constant;

@Path(Constant.BASE_CONTEXT + "/mesas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = Constant.TAG_2_4)
public class MesaController {

    @Inject
    MesaServicio mesaServicio;

    @GET
    @Operation(summary = "Obtener todas las mesas", description = "Devuelve la lista de mesas")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response listar() {
        RespuestaBaseResponse vResponse = mesaServicio.listar();
        return Response.ok(vResponse).build();
    }

    @GET
    @Path("/estado/{estado}")
    @Operation(summary = "Obtener mesas por estado", description = "Devuelve las mesas según su estado (LIBRE, OCUPADA, RESERVADA)")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response listarPorEstado(@PathParam("estado") String estado) {
        RespuestaBaseResponse vResponse = mesaServicio.listarPorEstado(estado);
        return Response.ok(vResponse).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Obtener mesa por ID", description = "Devuelve una mesa según su ID")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response buscarPorId(@PathParam("id") Long id) {
        RespuestaBaseResponse vResponse = mesaServicio.buscarPorId(id);
        return Response.ok(vResponse).build();
    }

    @POST
    @Operation(summary = "Guardar mesa", description = "Guarda una nueva mesa")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response guardar(MesaDTO mesaDTO) {
        RespuestaBaseResponse vResponse = mesaServicio.guardar(mesaDTO);
        return Response.ok(vResponse).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Actualizar mesa", description = "Actualiza una mesa existente")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response actualizar(@PathParam("id") Long id, MesaDTO mesaDTO) {
        mesaDTO.id = id;
        RespuestaBaseResponse vResponse = mesaServicio.actualizar(mesaDTO);
        return Response.ok(vResponse).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar mesa", description = "Elimina una mesa por su ID")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = Exception.class)
    public Response eliminar(@PathParam("id") Long id) {
        RespuestaBaseResponse vResponse = mesaServicio.eliminar(id);
        return Response.ok(vResponse).build();
    }
}
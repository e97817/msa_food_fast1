package bo.gob.food_fast.atencion.controller.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import bo.gob.food_fast.shared.constant.Constant;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


import bo.gob.food_fast.atencion.service.service.CelularServicio;
import bo.gob.food_fast.atencion.dto.dto.DtoCelular;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;



@Path(Constant.BASE_CONTEXT + "/atencion/celular")
public class CelularController
{
    CelularServicio celularServicio;

    @Inject
    CelularController(CelularServicio pCelularServicio){
        this.celularServicio = pCelularServicio;
    }

    /**
    * Método que permite bla bla.
    * @author Wilson Limachi Choque.
    * @since 26/10/2024
    *
    * @param  pRequest, datos requeridos para la obtencion de datos
    * @return BusquedaTramitadorResponse, respuesta con los datos de expedidos y tipos de documento de identificación.
    * @throws Exception
    */
    @POST
    @Path("/obtener-datos-celular")
    @Tag(name = Constant.TAG_1_1)
    @Operation(
            summary     = "Permite obtener los datos xx.",
            description = "Permite obtener los datos xx."
    )
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {IOException.class, Exception.class})
    public Response obtenerCelularServicioPorId(
                        @Valid DtoCelular pRequest) throws Exception {
        RespuestaBaseResponse vResponse = this.celularServicio.obtenerCelularPorId(pRequest.numSec);
        return Response.status(Response.Status.ACCEPTED).entity(vResponse).build();
    }


    /**
    * Método que permite bla bla.
    * @author Wilson Limachi Choque.
    * @since 26/10/2024
    *
    * @return List<DtoCelular>, respuesta .
    * @throws Exception
    */
    @POST
    @Path("/obtener-datos-celularList")
    @Tag(name = Constant.TAG_1_1)
    @Operation(
            summary     = "Permite obtener los datos xx.",
            description = "Permite obtener los datos xx."
    )
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {IOException.class, Exception.class})
    public Response obtenerCelularServicioList(
                        ) throws Exception {
        RespuestaBaseResponse vResponse = this.celularServicio.obtenerCelularList();
        return Response.status(Response.Status.ACCEPTED).entity(vResponse).build();
    }

    /**
     * Método que permite bla bla.
     * @author Wilson Limachi Choque.
     * @since 26/10/2024
     *
     * @param  pRequest, datos requeridos para la obtencion de datos
     * @return Celular, respuesta con los datos de expedidos y tipos de documento de identificación.
     * @throws Exception
     */
    @POST
    @Path("/registrarCelular")
    @Tag(name = Constant.TAG_1_1)
    @Operation(
            summary     = "Permite obtener los datos xx.",
            description = "Permite obtener los datos xx."
    )
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {IOException.class, Exception.class})
    public Response registrarCelular(
            @Valid DtoCelular pRequest) throws Exception {
        RespuestaBaseResponse vResponse = this.celularServicio.registrarCelular(pRequest);
        return Response.status(Response.Status.ACCEPTED).entity(vResponse).build();
    }
    /**
     * Método que permite bla bla.
     * @author Wilson Limachi Choque.
     * @since 26/10/2024
     *
     * @param  pRequest, datos requeridos para la obtencion de datos
     * @return Celular, respuesta con los datos de expedidos y tipos de documento de identificación.
     * @throws Exception
     */
    @POST
    @Path("/modificarCelular")
    @Tag(name = Constant.TAG_1_1)
    @Operation(
            summary     = "Permite modificar los datos xx.",
            description = "Permite modificar los datos xx."
    )
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {IOException.class, Exception.class})
    public Response modificarCelular(
            @Valid DtoCelular pRequest) throws Exception {
        RespuestaBaseResponse vResponse = this.celularServicio.modificarCelular(pRequest);
        return Response.status(Response.Status.ACCEPTED).entity(vResponse).build();
    }
}

package bo.gob.food_fast.atencion.controller.controller;

import bo.gob.food_fast.atencion.dto.dto.response.ReporteResponse;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;
import bo.gob.food_fast.atencion.service.service.ReporteServicio;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/reportes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReporteController {

    @Inject
    ReporteServicio reporteServicio;

    @GET
    @Path("/celulares/pdf")
    public RespuestaBaseResponse descargarReporteCelulares(
            @QueryParam("fechaDesde") String fechaDesde,
            @QueryParam("fechaHasta") String fechaHasta) {
        
        RespuestaBaseResponse response = new RespuestaBaseResponse();
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("fechaDesde", fechaDesde);
        filtros.put("fechaHasta", fechaHasta);

        try {
            ReporteResponse reporte = reporteServicio.generarReporteCelulares(filtros);
            
            response.success = true;
            response.mensajes = List.of("Reporte generado exitosamente");
            response.respuesta = reporte;
        } catch (Exception e) {
            response.success = false;
            response.mensajes = List.of("Error al generar el reporte: " + e.getMessage());
            response.respuesta = null;
        }

        return response;
    }
}

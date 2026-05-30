package bo.gob.food_fast.atencion.service.service;

import bo.gob.food_fast.atencion.dto.dto.response.ReporteResponse;
import java.util.Map;

public interface ReporteServicio {
    ReporteResponse generarReporteCelulares(Map<String, Object> filtros);
}

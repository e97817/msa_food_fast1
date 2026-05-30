package bo.gob.food_fast.atencion.service.service;

import java.util.List;

import bo.gob.food_fast.atencion.dto.dto.DtoCelular;
import bo.gob.food_fast.atencion.dto.dto.response.RespuestaBaseResponse;

/**
 * @author Wilson Limachi Choque
 * @since 16/05/2025
 * @Actividad --
 */
public interface CelularServicioLocal
{	
	public RespuestaBaseResponse obtenerCelularPorId(Long pNumSec);
	public RespuestaBaseResponse obtenerCelularList();
	public RespuestaBaseResponse registrarCelular(DtoCelular pDatosCelular);
	public RespuestaBaseResponse modificarCelular(DtoCelular pDatosCelular);
}
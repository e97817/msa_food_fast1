package bo.gob.food_fast.atencion.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
/**
 * @author Wilson Limachi Choque
 * @since 16/05/2025
 * @Actividad --
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCelular implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Long numSec;
	public BigDecimal nsecVeRegAduana;
	public Date fechaCreacion;
	public String estado;
	public String observaciones;
}
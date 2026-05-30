package bo.gob.food_fast.atencion.dto.dto.response;

import java.util.List;

import lombok.Data;
@Data
public class RespuestaBaseResponse<T> {
    public boolean success;
	public List<String> mensajes;

    public T respuesta;
}
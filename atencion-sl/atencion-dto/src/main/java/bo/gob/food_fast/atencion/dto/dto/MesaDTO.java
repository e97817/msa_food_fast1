package bo.gob.food_fast.atencion.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long id;
    public Integer numeroMesa;
    public Integer capacidad;
    public String estado;
}
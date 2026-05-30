package bo.gob.food_fast.atencion.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long id;
    public String nombres;
    public String apellidos;
    public String telefono;
    public String direccion;
    public String correo;
    public Date fechaRegistro;
    public List<PedidoDTO> pedidos;
}
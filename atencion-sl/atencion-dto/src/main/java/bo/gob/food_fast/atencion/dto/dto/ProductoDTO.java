package bo.gob.food_fast.atencion.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long id;
    public String nombre;
    public String descripcion;
    public BigDecimal precio;
    public String categoria;
    public Boolean disponible;
    public List<DetallePedidoDTO> detallesPedido;
}
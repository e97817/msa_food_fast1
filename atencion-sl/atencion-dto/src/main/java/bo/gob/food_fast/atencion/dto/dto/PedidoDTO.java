package bo.gob.food_fast.atencion.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long id;
    public Long clienteId;
    public ClienteDTO cliente;
    public Date fechaPedido;
    public String estado;
    public BigDecimal total;
    public List<DetallePedidoDTO> detallesPedido;
}
package bo.gob.food_fast.atencion.dto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedidoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long id;
    public Long pedidoId;
    public PedidoDTO pedido;
    public Long productoId;
    public ProductoDTO producto;
    public Integer cantidad;
    public BigDecimal precioUnitario;
    public BigDecimal subtotal;
}
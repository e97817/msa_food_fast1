package bo.gob.food_fast.atencion.entity.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "detalle_pedido", schema = "public")
public class DetallePedidoJPA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    public PedidoJPA pedido;

    @Column(name = "pedido_id", insertable = false, updatable = false)
    public Long pedidoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    public ProductoJPA producto;

    @Column(name = "producto_id", insertable = false, updatable = false)
    public Long productoId;

    @Column(name = "cantidad", nullable = false)
    public Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    public BigDecimal precioUnitario;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    public BigDecimal subtotal;

    @Override
    public String toString() {
        return "DetallePedidoJPA{" +
                "id=" + id +
                ", pedidoId=" + pedidoId +
                ", productoId=" + productoId +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}
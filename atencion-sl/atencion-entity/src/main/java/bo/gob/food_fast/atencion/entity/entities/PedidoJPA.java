package bo.gob.food_fast.atencion.entity.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "pedido", schema = "public")
public class PedidoJPA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    public ClienteJPA cliente;

    @Column(name = "cliente_id", insertable = false, updatable = false)
    public Long clienteId;

    @Column(name = "fecha_pedido", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaPedido;

    @Column(name = "estado", nullable = false, length = 20)
    public String estado;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    public BigDecimal total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DetallePedidoJPA> detallesPedido;

    @Override
    public String toString() {
        return "PedidoJPA{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", estado='" + estado + '\'' +
                ", total=" + total +
                '}';
    }
}
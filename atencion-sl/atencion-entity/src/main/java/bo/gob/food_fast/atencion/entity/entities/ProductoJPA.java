package bo.gob.food_fast.atencion.entity.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "producto", schema = "public")
public class ProductoJPA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    public String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    public String descripcion;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    public BigDecimal precio;

    @Column(name = "categoria", length = 50)
    public String categoria;

    @Column(name = "disponible", nullable = false)
    public Boolean disponible;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DetallePedidoJPA> detallesPedido;

    @Override
    public String toString() {
        return "ProductoJPA{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio=" + precio +
                ", disponible=" + disponible +
                '}';
    }
}
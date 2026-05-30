package bo.gob.food_fast.atencion.entity.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "mesa", schema = "public")
public class MesaJPA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "numero_mesa", nullable = false, unique = true)
    public Integer numeroMesa;

    @Column(name = "capacidad", nullable = false)
    public Integer capacidad;

    @Column(name = "estado", nullable = false, length = 20)
    public String estado;

    @Override
    public String toString() {
        return "MesaJPA{" +
                "id=" + id +
                ", numeroMesa=" + numeroMesa +
                ", capacidad=" + capacidad +
                ", estado='" + estado + '\'' +
                '}';
    }
}
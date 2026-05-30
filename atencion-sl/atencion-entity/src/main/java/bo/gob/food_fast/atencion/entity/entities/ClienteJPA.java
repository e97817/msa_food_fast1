package bo.gob.food_fast.atencion.entity.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "cliente", schema = "public")
public class ClienteJPA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nombres", nullable = false, length = 100)
    public String nombres;

    @Column(name = "apellidos", nullable = false, length = 100)
    public String apellidos;

    @Column(name = "telefono", length = 20)
    public String telefono;

    @Column(name = "direccion", columnDefinition = "TEXT")
    public String direccion;

    @Column(name = "correo", length = 100, unique = true)
    public String correo;

    @Column(name = "fecha_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaRegistro;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<PedidoJPA> pedidos;

    @Override
    public String toString() {
        return "ClienteJPA{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
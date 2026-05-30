package bo.gob.food_fast.atencion.entity.entities;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author Wilson Limachi Choque
 * @since 16/05/2025
 * @Actividad --
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "celular", schema = "public")
public class CelularJPA implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="num_sec")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long numSec;

	@Column(name="nsec_ve_reg_aduana")
	public BigDecimal nsecVeRegAduana;

	@Column(name="fecha_creacion")
	public Date fechaCreacion;

	@Column(name="estado")
	public String estado;

	@Column(name="observaciones")
	public String observaciones;

}
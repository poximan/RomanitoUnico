/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "fardo")
public class Fardo extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	private Integer id;

	@ManyToOne(optional = false /* JPA lo resuelve en tiempo de ejecucion */)
	@JoinColumn(name = "TIPO_TARJETA", referencedColumnName = "ID", nullable = false)
	private TipoTarjeta tipoTarjeta;

	@ManyToOne(optional = false /* JPA lo resuelve en tiempo de ejecucion */)
	@JoinColumn(name = "CLASE_LANA", referencedColumnName = "ID", nullable = false)
	private ClaseLana claseLana;

	@ManyToOne(optional = false /* JPA lo resuelve en tiempo de ejecucion */)
	@JoinColumn(name = "CATEGORIA_ANIMAL", referencedColumnName = "ID", nullable = false)
	private CategoriaAnimal categoriaAnimal;

	@ManyToOne(optional = false /* JPA lo resuelve en tiempo de ejecucion */)
	@JoinColumn(name = "ROMANEO", referencedColumnName = "ID", nullable = false)
	private Romaneo romaneo;

	@Column(name = "PESO", nullable = true)
	private float peso;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Fardo() {

	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	@Override
	public Object getPK() {
		return id;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	@Override
	public void setPK(Object id) {
		this.id = (Integer) id;
	}
}

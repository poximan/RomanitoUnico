/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "romaneo")
public class Romaneo extends BaseEntity implements Serializable {

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
	@JoinColumn(name = "ACONDICIONADOR", referencedColumnName = "ID", nullable = false)
	private Acondicionador acondicionador;

	@ManyToOne(optional = false /* JPA lo resuelve en tiempo de ejecucion */)
	@JoinColumn(name = "ESTABLECIMIENTO", referencedColumnName = "ID", nullable = false)
	private Establecimiento establecimiento;

	@ManyToOne(optional = false /* JPA lo resuelve en tiempo de ejecucion */)
	@JoinColumn(name = "CONTRATISTA", referencedColumnName = "ID", nullable = false)
	private Contratista contratista;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "FARDOS", nullable = true)
	private Collection<Fardo> fardos;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "MUESTRAS", nullable = true)
	private Collection<Muestra> muestras;

	@Column(name = "FECHA_INICIO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar inicio;

	@Column(name = "FECHA_FIN", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fin;

	@Column(name = "ESTADO", nullable = true)
	private int estado;

	@Column(name = "CABEZAS", nullable = true)
	private int cabezas;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Romaneo() {

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

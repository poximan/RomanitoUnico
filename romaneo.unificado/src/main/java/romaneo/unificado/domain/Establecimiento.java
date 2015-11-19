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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "establecimiento")
@NamedQuery(name = "establecimiento.buscarPorId", query = "SELECT tabla FROM Establecimiento tabla WHERE tabla.id = ?1")
public class Establecimiento extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre_establecimiento;

	@ManyToOne
	private Productor productor;

	@ManyToOne
	private Partido partido;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Establecimiento() {
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Establecimiento)) {
			return false;
		}
		Establecimiento other = (Establecimiento) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	public enum Filters {

		BY_NOMBRE("nombre");

		private String value;

		public String getValue() {
			return value;
		}

		Filters(String value) {
			this.value = value;
		}
	}

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	public Integer getId() {
		return id;
	}

	public String getNombre_establecimiento() {
		return nombre_establecimiento;
	}

	public Productor getProductor() {
		return productor;
	}

	public Partido getPartido() {
		return partido;
	}

	@Override
	public Object getPK() {

		return id;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre_establecimiento(String nombre_establecimiento) {
		this.nombre_establecimiento = nombre_establecimiento;
	}

	public void setProductor(Productor productor) {
		this.productor = productor;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	@Override
	public void setPK(Object id) {

		this.id = (Integer) id;
	}
}

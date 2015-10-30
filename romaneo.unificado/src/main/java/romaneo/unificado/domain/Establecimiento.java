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
import javax.persistence.TableGenerator;

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

	@Id
	@Column(name = "ID_ESTABLECIMIENTO")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_establecimiento")
	@TableGenerator(name = "gen_establecimiento", initialValue = 1, allocationSize = 1)
	private Integer id;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre_establecimiento;

	@ManyToOne
	private Productor productor;

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

	public enum Filters {

		BY_FIRST_NAME("nombre");

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

	@Override
	public void setPK(Object id) {

		this.id = (Integer) id;
	}
}

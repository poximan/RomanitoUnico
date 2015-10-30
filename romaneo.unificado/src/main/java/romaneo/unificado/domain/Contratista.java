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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "contratista")
@NamedQuery(name = "contratista.buscarPorId", query = "SELECT tabla FROM Contratista tabla WHERE tabla.id = ?1")
public class Contratista extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CONTRATISTA")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_contratista")
	@TableGenerator(name = "gen_contratista", initialValue = 1, allocationSize = 1)
	private Integer id;

	@Column(name = "NOMBRE", length = 50)
	private String nombre;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Contratista() {
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

	public String getNombre() {
		return nombre;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void setPK(Object id) {

		this.id = (Integer) id;
	}
}

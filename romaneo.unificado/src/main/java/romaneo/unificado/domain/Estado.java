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
import javax.persistence.Table;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "estado")
public class Estado extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOMBRE")
	private String nombre;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Estado() {
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
		if (!(object instanceof Estado)) {
			return false;
		}
		Estado other = (Estado) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {

		return nombre;
	}

	public enum EstadosPosibles {

		GENERADO("generado"), ENVIADO("enviado"), ERROR("error"), RECIBIDO("recibido"), LEIDO("leido");

		private String value;

		public String getValue() {
			return value;
		}

		EstadosPosibles(String value) {
			this.value = value;
		}
	}

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	@Override
	public Object getPK() {
		return this.id;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
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
	public void setPK(Object Id) {
		this.id = (Integer) Id;
	}
}

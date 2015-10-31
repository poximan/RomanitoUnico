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
@Table(name = "localidad")
public class Localidad extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "LOCALIDAD")
	private String localidad;

	@Column(name = "COD_POSTAL")
	private String codigo_postal;

	@JoinColumn(name = "ID_PARTIDO", referencedColumnName = "id")
	@ManyToOne
	private Partido partido;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Localidad() {
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
		if (!(object instanceof Localidad)) {
			return false;
		}
		Localidad other = (Localidad) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return localidad;
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

	public String getLocalidad() {
		return localidad;
	}

	public String getCodigo_postal() {
		return codigo_postal;
	}

	public Partido getPartido() {
		return partido;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	@Override
	public void setPK(Object id) {
		this.id = (Integer) id;
	}
}

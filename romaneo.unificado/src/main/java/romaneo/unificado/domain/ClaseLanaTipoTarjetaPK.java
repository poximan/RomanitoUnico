/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Embeddable
public class ClaseLanaTipoTarjetaPK implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "ID_CLASE_LANA", nullable = false)
	private int id_clase_lana;

	@Basic(optional = false)
	@Column(name = "ID_TIPO_TARJETA", nullable = false)
	private int id_tipo_tarjeta;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public ClaseLanaTipoTarjetaPK() {
	}

	public ClaseLanaTipoTarjetaPK(int id_clase_lana, int id_tipo_tarjeta) {
		this.id_clase_lana = id_clase_lana;
		this.id_tipo_tarjeta = id_tipo_tarjeta;
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) id_clase_lana;
		hash += (int) id_tipo_tarjeta;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ClaseLanaTipoTarjetaPK)) {
			return false;
		}
		ClaseLanaTipoTarjetaPK other = (ClaseLanaTipoTarjetaPK) object;
		if (this.id_clase_lana != other.id_clase_lana) {
			return false;
		}
		if (this.id_tipo_tarjeta != other.id_tipo_tarjeta) {
			return false;
		}
		return true;
	}

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	public int getId_clase_lana() {
		return id_clase_lana;
	}

	public int getId_tipo_tarjeta() {
		return id_tipo_tarjeta;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId_clase_lana(int id_clase_lana) {
		this.id_clase_lana = id_clase_lana;
	}

	public void setId_tipo_tarjeta(int id_tipo_tarjeta) {
		this.id_tipo_tarjeta = id_tipo_tarjeta;
	}
}

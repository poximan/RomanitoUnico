/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "clase_lana_tipo_tarjeta")
public class ClaseLanaTipoTarjeta extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected ClaseLanaTipoTarjetaPK clase_lana_tipo_tarjeta_PK;

	@JoinColumn(name = "ID_CLASE_LANA", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private ClaseLana id_clase_lana;

	@JoinColumn(name = "ID_TIPO_TARJETA", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private TipoTarjeta id_tipo_tarjeta;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public ClaseLanaTipoTarjeta() {
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (clase_lana_tipo_tarjeta_PK != null ? clase_lana_tipo_tarjeta_PK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ClaseLanaTipoTarjeta)) {
			return false;
		}
		ClaseLanaTipoTarjeta other = (ClaseLanaTipoTarjeta) object;
		if ((this.clase_lana_tipo_tarjeta_PK == null && other.clase_lana_tipo_tarjeta_PK != null)
				|| (this.clase_lana_tipo_tarjeta_PK != null
						&& !this.clase_lana_tipo_tarjeta_PK.equals(other.clase_lana_tipo_tarjeta_PK))) {
			return false;
		}
		return true;
	}

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	@Override
	public Object getPK() {
		return clase_lana_tipo_tarjeta_PK;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	@Override
	public void setPK(Object Id) {
		this.clase_lana_tipo_tarjeta_PK = (ClaseLanaTipoTarjetaPK) Id;
	}
}
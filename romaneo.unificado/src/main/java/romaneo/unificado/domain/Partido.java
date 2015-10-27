package romaneo.unificado.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** @author nacho */
@Entity
@Table(name = "partido")
public class Partido extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "codpart")
	private String codpart;

	@Column(name = "partido")
	private String partido;

	@JoinColumn(name = "provincia", referencedColumnName = "codprov")
	@ManyToOne
	private Provincia provincia;

	public Partido() {
	}

	public Partido(String codpart) {
		this.codpart = codpart;
	}

	public String getCodpart() {
		return codpart;
	}

	public void setCodpart(String codpart) {
		this.codpart = codpart;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codpart != null ? codpart.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Partido)) {
			return false;
		}
		Partido other = (Partido) object;
		if ((this.codpart == null && other.codpart != null)
				|| (this.codpart != null && !this.codpart.equals(other.codpart))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Entities.Partido[ codpart=" + codpart + " ]";
	}

	@Override
	public Object getPK() {
		return this.codpart;
	}

	@Override
	public void setPK(Object Id) {
		this.codpart = (String) Id;

	}

}
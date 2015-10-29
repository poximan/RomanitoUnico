package romaneo.unificado.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author nacho
 */
@Entity
@Table(name = "localidad")
public class Localidad extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "codloc")
	private String codloc;

	@Column(name = "localidad")
	private String localidad;

	@Column(name = "codpostal")
	private String codpostal;

	@JoinColumn(name = "codpart", referencedColumnName = "codpart")
	@ManyToOne
	private Partido codpart;

	public Localidad() {
	}

	public Localidad(String codloc) {
		this.codloc = codloc;
	}

	public String getCodloc() {
		return codloc;
	}

	public void setCodloc(String codloc) {
		this.codloc = codloc;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCodpostal() {
		return codpostal;
	}

	public void setCodpostal(String codpostal) {
		this.codpostal = codpostal;
	}

	public Partido getCodpart() {
		return codpart;
	}

	public void setCodpart(Partido codpart) {
		this.codpart = codpart;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codloc != null ? codloc.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Localidad)) {
			return false;
		}
		Localidad other = (Localidad) object;
		if ((this.codloc == null && other.codloc != null)
				|| (this.codloc != null && !this.codloc.equals(other.codloc))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return localidad;
	}

	@Override
	public Object getPK() {
		return this.codloc;
	}

	@Override
	public void setPK(Object Id) {
		this.codloc = (String) Id;

	}

}

package romaneo.unificado.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** @author nacho */
@Entity
@Table(name = "provincia")
public class Provincia extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "codprov")
	private String codprov;

	@Column(name = "provincia")
	private String provincia;

	public Provincia() {
	}

	public Provincia(String codprov) {
		this.codprov = codprov;
	}

	public String getCodprov() {
		return codprov;
	}

	public void setCodprov(String codprov) {
		this.codprov = codprov;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codprov != null ? codprov.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Provincia)) {
			return false;
		}
		Provincia other = (Provincia) object;
		if ((this.codprov == null && other.codprov != null)
				|| (this.codprov != null && !this.codprov.equals(other.codprov))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Entities.Provincia[ codprov=" + codprov + " ]";
	}

	@Override
	public Object getPK() {
		return this.codprov;
	}

	@Override
	public void setPK(Object Id) {
		this.codprov = (String) Id;

	}

}

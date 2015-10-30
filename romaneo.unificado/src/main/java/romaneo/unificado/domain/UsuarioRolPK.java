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
public class UsuarioRolPK implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "ID_USUARIO", nullable = false)
	private int id_usuario;

	@Basic(optional = false)
	@Column(name = "ID_ROL", nullable = false)
	private int id_rol;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public UsuarioRolPK() {
	}

	public UsuarioRolPK(int id_usuario, int id_rol) {
		this.id_usuario = id_usuario;
		this.id_rol = id_rol;
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) id_usuario;
		hash += (int) id_rol;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UsuarioRolPK)) {
			return false;
		}
		UsuarioRolPK other = (UsuarioRolPK) object;
		if (this.id_usuario != other.id_usuario) {
			return false;
		}
		if (this.id_rol != other.id_rol) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "domain.UsuarioRolPK[ usuario id=" + id_usuario + ", rol id=" + id_rol + " ]";
	}

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	public int getId_usuario() {
		return id_usuario;
	}

	public int getId_rol() {
		return id_rol;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
}

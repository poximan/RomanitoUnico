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
public class UsuarioMovilPK implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "ID_USUARIO", nullable = false)
	private int id_usuario;

	@Basic(optional = false)
	@Column(name = "ID_MOVIL", nullable = false)
	private int id_movil;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public UsuarioMovilPK() {
	}

	public UsuarioMovilPK(int id_usuario, int id_rol) {
		this.id_usuario = id_usuario;
		this.id_movil = id_rol;
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) id_usuario;
		hash += (int) id_movil;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UsuarioMovilPK)) {
			return false;
		}
		UsuarioMovilPK other = (UsuarioMovilPK) object;
		if (this.id_usuario != other.id_usuario) {
			return false;
		}
		if (this.id_movil != other.id_movil) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "domain.UsuarioRolPK[ usuario id=" + id_usuario + ", rol id=" + id_movil + " ]";
	}

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	public int getId_usuario() {
		return id_usuario;
	}

	public int getId_movil() {
		return id_movil;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public void setId_movil(int id_movil) {
		this.id_movil = id_movil;
	}
}

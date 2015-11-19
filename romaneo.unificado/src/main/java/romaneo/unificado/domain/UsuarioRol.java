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
@Table(name = "usuario_rol")
public class UsuarioRol extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected UsuarioRolPK usuarioRolPK;

	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Usuario id_usuario;

	@JoinColumn(name = "ID_ROL", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Rol id_rol;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public UsuarioRol() {
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (usuarioRolPK != null ? usuarioRolPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UsuarioRol)) {
			return false;
		}
		UsuarioRol other = (UsuarioRol) object;
		if ((this.usuarioRolPK == null && other.usuarioRolPK != null)
				|| (this.usuarioRolPK != null && !this.usuarioRolPK.equals(other.usuarioRolPK))) {
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
		return usuarioRolPK;
	}

	public UsuarioRolPK getUsuarioRolPK() {
		return usuarioRolPK;
	}

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public Rol getId_rol() {
		return id_rol;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setUsuarioRolPK(UsuarioRolPK usuarioRolPK) {
		this.usuarioRolPK = usuarioRolPK;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}

	public void setId_rol(Rol id_rol) {
		this.id_rol = id_rol;
	}

	@Override
	public void setPK(Object Id) {
		this.usuarioRolPK = (UsuarioRolPK) Id;
	}

}
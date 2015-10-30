/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Basic(optional = false)
	@Column(name = "ESTA_ACTIVO", nullable = false)
	private Character activo;

	@Basic(optional = false)
	@Column(name = "FECHA_CREACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha_creacion;

	@Basic(optional = false)
	@Column(name = "CREADO_POR", nullable = false)
	private int creado_por;

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

	@Override
	public String toString() {
		return "domain.UsuarioRol[ UsuarioRolPK=" + usuarioRolPK + " ]";
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

	public Character getActivo() {
		return activo;
	}

	public Calendar getFecha_creacion() {
		return fecha_creacion;
	}

	public int getCreado_por() {
		return creado_por;
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

	public void setActivo(Character activo) {
		this.activo = activo;
	}

	public void setFecha_creacion(Calendar fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public void setCreado_por(int creado_por) {
		this.creado_por = creado_por;
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
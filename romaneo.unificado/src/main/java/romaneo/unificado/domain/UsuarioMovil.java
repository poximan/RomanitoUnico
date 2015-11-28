/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.Calendar;

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
@Table(name = "usuario_movil")
public class UsuarioMovil extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected UsuarioMovilPK usuarioMovilPK;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private Usuario idUsuario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_MOVIL", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	private Movil idMovil;

	@Column(name = "FECHA_INICIO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaInicio;

	@Column(name = "FECHA_FIN", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaFin;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public UsuarioMovil() {
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (usuarioMovilPK != null ? usuarioMovilPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UsuarioMovil)) {
			return false;
		}
		UsuarioMovil other = (UsuarioMovil) object;
		if ((this.usuarioMovilPK == null && other.usuarioMovilPK != null)
				|| (this.usuarioMovilPK != null && !this.usuarioMovilPK.equals(other.usuarioMovilPK))) {
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
		return usuarioMovilPK;
	}

	public UsuarioMovilPK getUsuarioMovilPK() {
		return usuarioMovilPK;
	}

	public Usuario getId_usuario() {
		return idUsuario;
	}

	public Movil getId_movil() {
		return idMovil;
	}

	public Calendar getFecha_inicio() {
		return fechaInicio;
	}

	public Calendar getFecha_fin() {
		return fechaFin;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setFecha_inicio(Calendar fecha_inicio) {
		this.fechaInicio = fecha_inicio;
	}

	public void setFecha_fin(Calendar fecha_fin) {
		this.fechaFin = fecha_fin;
	}

	public void setUsuarioMovilPK(UsuarioMovilPK usuarioMovilPK) {
		this.usuarioMovilPK = usuarioMovilPK;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.idUsuario = id_usuario;
	}

	public void setId_movil(Movil id_movil) {
		this.idMovil = id_movil;
	}

	@Override
	public void setPK(Object id) {
		this.usuarioMovilPK = (UsuarioMovilPK) id;
	}
}
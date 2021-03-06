/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "usuario")
public class Usuario extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
	private Persona persona;

	@Basic(optional = false)
	@Column(name = "NOMBRE_USUARIO", nullable = false, length = 40)
	private String nombreUsuario;

	@Column(name = "CLAVE_USUARIO", length = 40)
	private String claveUsuario;

	@Column(name = "DESCRIPCION", length = 100)
	private String descripcion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id_usuario")
	private List<UsuarioRol> roles = new ArrayList<UsuarioRol>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private List<UsuarioMovil> moviles = new ArrayList<UsuarioMovil>();

	@Basic(optional = false)
	@Column(name = "ESTA_ACTIVO", nullable = false)
	private Character activo;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Usuario() {
	}

	public Usuario(Persona persona, String nombreUsuario, String claveUsuario, String descripcion) {
		super();
		this.persona = persona;
		this.nombreUsuario = nombreUsuario;
		this.claveUsuario = claveUsuario;
		this.descripcion = descripcion;
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return persona.toString();
	}

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	@Override
	public Object getPK() {
		return id;
	}

	public Integer getId() {
		return id;
	}

	public Persona getPersona() {
		return persona;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<UsuarioRol> getRoles() {
		return roles;
	}

	public Character getActivo() {
		return activo;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	@Override
	public void setPK(Object id) {
		this.id = (Integer) id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setRoles(List<UsuarioRol> roles) {
		this.roles = roles;
	}

	public void setActivo(Character activo) {
		this.activo = activo;
	}
}
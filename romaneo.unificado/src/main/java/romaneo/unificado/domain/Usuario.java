/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;
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
	@JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
	private Persona persona;

	@Basic(optional = false)
	@Column(name = "NOMBRE_USUARIO", nullable = false, length = 40)
	private String nombre_usuario;

	@Column(name = "CLAVE_USUARIO", length = 40)
	private String clave_usuario;

	@Column(name = "DESCRIPCION", length = 100)
	private String descripcion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id_usuario")
	private List<UsuarioRol> roles;

	@Basic(optional = false)
	@Column(name = "ESTA_ACTIVO", nullable = false)
	private Character activo;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Usuario() {
	}

	public Usuario(Persona persona, String nombre_usuario, String clave_usuario, String descripcion) {
		super();
		this.persona = persona;
		this.nombre_usuario = nombre_usuario;
		this.clave_usuario = clave_usuario;
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

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public String getClave_usuario() {
		return clave_usuario;
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

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public void setClave_usuario(String clave_usuario) {
		this.clave_usuario = clave_usuario;
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
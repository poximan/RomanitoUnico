/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@Basic(optional = false)
	@Column(name = "FECHA_MODIFICACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha_modificado;

	@Basic(optional = false)
	@Column(name = "MODIFICADO_POR", nullable = false)
	private int modificado_por;

	@Basic(optional = false)
	@Column(name = "NOMBRE", nullable = false, length = 60)
	private String nombre;

	@Column(name = "DESCRIPCION", length = 255)
	private String descripcion;

	@Column(name = "PASSWORD", length = 40)
	private String password;

	@Column(name = "EMAIL", length = 60)
	private String email;

	@Column(name = "TELEFONO", length = 40)
	private String telefono;

	@Column(name = "FECHA_NACIMIENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha_nacimiento;

	@Basic(optional = false)
	@Column(name = "ACCESO_SISTEMA", nullable = false)
	private Character acceso_sistema;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id_usuario")
	private List<UsuarioRol> lista_adUserRoles;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Usuario() {
	}

	public Usuario(Integer id) {
		this.id = id;
	}

	public Usuario(Integer id, Character activo, Calendar fecha_creacion, int creado_por, Calendar fecha_modificado,
			int modificado_por, String nombre, Character acceso_sistema) {

		this.id = id;
		this.activo = activo;
		this.fecha_creacion = fecha_creacion;
		this.creado_por = creado_por;
		this.fecha_modificado = fecha_modificado;
		this.modificado_por = modificado_por;
		this.nombre = nombre;
		this.acceso_sistema = acceso_sistema;
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
		return "domain.usuario[ id=" + id + " Name=" + nombre + "]";
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

	public Character getActivo() {
		return activo;
	}

	public Calendar getFecha_creacion() {
		return fecha_creacion;
	}

	public int getCreado_por() {
		return creado_por;
	}

	public Calendar getFecha_modificado() {
		return fecha_modificado;
	}

	public int getModificado_por() {
		return modificado_por;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

	public Calendar getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public Character getAcceso_sistema() {
		return acceso_sistema;
	}

	public List<UsuarioRol> getLista_adUserRoles() {
		return lista_adUserRoles;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId(Integer id) {
		this.id = id;
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

	public void setFecha_modificado(Calendar fecha_modificado) {
		this.fecha_modificado = fecha_modificado;
	}

	public void setModificado_por(int modificado_por) {
		this.modificado_por = modificado_por;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setFecha_nacimiento(Calendar fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public void setAcceso_sistema(Character acceso_sistema) {
		this.acceso_sistema = acceso_sistema;
	}

	public void setLista_adUserRoles(List<UsuarioRol> lista_adUserRoles) {
		this.lista_adUserRoles = lista_adUserRoles;
	}

	@Override
	public void setPK(Object id) {
		this.id = (Integer) id;
	}
}
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
@Table(name = "rol")
public class Rol extends BaseEntity implements Serializable {

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
	@Column(name = "NOMBRE", nullable = false, length = 60)
	private String nombre;

	@Column(name = "DESCRIPCION", length = 255)
	private String descripcion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id_rol")
	private List<UsuarioRol> lista_adUserRoles;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Rol() {
	}

	public Rol(Integer id) {
		this.id = id;
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
		if (!(object instanceof Rol)) {
			return false;
		}
		Rol other = (Rol) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "domain.AdRole[ id=" + id + " ]";
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

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<UsuarioRol> getLista_adUserRoles() {
		return lista_adUserRoles;
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

	public void setActivo(Character activo) {
		this.activo = activo;
	}

	public void setFecha_creacion(Calendar fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public void setCreado_por(int creado_por) {
		this.creado_por = creado_por;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setLista_adUserRoles(List<UsuarioRol> lista_adUserRoles) {
		this.lista_adUserRoles = lista_adUserRoles;
	}
}
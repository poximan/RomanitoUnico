/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "acondicionador")
public class Acondicionador extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NOMBRE", length = 50)
	private String nombre;

	@Basic(optional = false)
	@Column(name = "APELLIDO", nullable = false, length = 50)
	@NotNull
	private String apellido;

	@Basic(optional = false)
	@Column(name = "DIRECCION", nullable = false, length = 150)
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9-_:., ]{1,150}")
	private String direccion;

	@Basic(optional = false)
	@Column(name = "DNI", nullable = false)
	@NotNull
	private Integer dni;

	@Column(name = "TELEFONO", length = 30)
	@Pattern(regexp = "[A-Za-z0-9-_:., ]{1,30}")
	private String telefono;

	@Column(name = "EMAIL", length = 70)
	private String email;

	@JoinColumn(name = "ID_LOCALIDAD", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false)
	@NotNull
	private Localidad id_localidad;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Acondicionador() {
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
		if (!(object instanceof Acondicionador)) {
			return false;
		}
		Acondicionador other = (Acondicionador) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return apellido + " " + nombre;
	}

	public String getFullName() {
		return apellido + " " + nombre;
	}

	public enum Filters {

		BY_FIRST_NAME("nombre"), BY_LAST_NAME("apellido"), BY_DNI("dni");

		private String value;

		public String getValue() {
			return value;
		}

		Filters(String value) {
			this.value = value;
		}
	}

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	@Override
	public Object getPK() {
		return this.id;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getDni() {
		return dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public Localidad getId_localidad() {
		return id_localidad;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId_localidad(Localidad localidad) {
		this.id_localidad = localidad;
	}

	@Override
	public void setPK(Object Id) {
		this.id = (Integer) Id;
	}
}

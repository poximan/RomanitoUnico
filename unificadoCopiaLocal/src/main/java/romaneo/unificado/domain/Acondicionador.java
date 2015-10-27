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

/** @author nacho */
@Entity
@Table(name = "acondicionador")
public class Acondicionador extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2019342400320322095L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@Column(name = "last_name", nullable = false, length = 50)
	@NotNull
	private String lastName;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Basic(optional = false)
	@Column(name = "address", nullable = false, length = 150)
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9-_:., ]{1,150}")
	private String address;

	@Basic(optional = false)
	@Column(name = "dni", nullable = false)
	@NotNull
	private Integer dni;

	@Column(name = "phones", length = 30)
	@Pattern(regexp = "[A-Za-z0-9-_:., ]{1,30}")
	private String phones;

	@Column(name = "mail", length = 70)
	private String mail;

	@JoinColumn(name = "codloc", referencedColumnName = "codloc", nullable = false)
	@ManyToOne(optional = false)
	@NotNull
	private Localidad codloc;

	public Acondicionador() {
	}

	@Override
	public Object getPK() {
		return this.id;
	}

	@Override
	public void setPK(Object Id) {
		this.id = (Integer) Id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Localidad getCodloc() {
		return codloc;
	}

	public void setCodloc(Localidad codloc) {
		this.codloc = codloc;
	}

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
		return lastName + " " + firstName;
	}

	public String getFullName() {
		return lastName + " " + firstName;
	}

	public enum Filters {

		BY_FIRST_NAME("firstName"), BY_LAST_NAME("lastName"), BY_DNI("dni");

		private String value;

		public String getValue() {
			return value;
		}

		Filters(String value) {
			this.value = value;
		}

	}

}

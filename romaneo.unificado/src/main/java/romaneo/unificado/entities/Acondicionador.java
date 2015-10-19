/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "acondicionador")
@NamedQuery(name = "acondicionador.buscarPorId", query = "SELECT tabla FROM Acondicionador tabla WHERE tabla.id = ?1")
public class Acondicionador implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_ACONDICIONADOR")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_acondicionador")
	@TableGenerator(name = "gen_acondicionador", initialValue = 1, allocationSize = 1)
	private long id;
	
	@OneToOne
	private Persona persona;
	
	@OneToOne
	private Contacto contacto;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Acondicionador() {
	}
	
	public Acondicionador(Persona persona, Contacto contacto)
	{
		this.persona = persona;
		this.contacto = contacto;
	}

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	public long getId() {
		return id;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId(long id) {
		this.id = id;
	}

	public Persona getPersona()
	{
		return persona;
	}

	public void setPersona(Persona persona)
	{
		this.persona = persona;
	}

	public Contacto getContacto()
	{
		return contacto;
	}

	public void setContacto(Contacto contacto)
	{
		this.contacto = contacto;
	}
}

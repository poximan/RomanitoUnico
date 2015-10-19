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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "persona")
@NamedQuery(name = "persona.buscarPorId", query = "SELECT tabla FROM Persona tabla WHERE tabla.id = ?1")
public class Persona implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_USUARIO")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_usuario")
	@TableGenerator(name = "gen_usuario", initialValue = 1, allocationSize = 1)
	private long id;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre;

	@Column(name = "APELLIDO", nullable = true)
	private String apellido;
	
	@Column(name = "DOCUMENTO")
	private long documento;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Persona() {
	}
	
	public Persona(String nombre, String apellido, long documento)
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
	}

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

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId(long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getDocumento()
	{
		return documento;
	}

	public void setDocumento(long documento)
	{
		this.documento = documento;
	}
	
	
}

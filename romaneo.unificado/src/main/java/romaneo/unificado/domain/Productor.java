/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Entity
@Table(name = "productor")
@NamedQuery(name = "productor.buscarPorId", query = "SELECT tabla FROM Productor tabla WHERE tabla.id = ?1")
public class Productor extends BaseEntity implements Serializable {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PRODUCTOR")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_productor")
	@TableGenerator(name = "gen_productor", initialValue = 1, allocationSize = 1)
	private Integer id;

	@Column(name = "NOMBRE", nullable = true)
	private String nombre_productor;

	@OneToMany(mappedBy = "productor")
	private List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public Productor() {
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	/* ............................................. */
	/* ............................................. */
	/* GET'S ....................................... */
	/* ............................................. */

	public Integer getId() {
		return id;
	}

	public String getNombre_productor() {
		return nombre_productor;
	}

	@Override
	public Object getPK() {

		return id;
	}

	/* ............................................. */
	/* ............................................. */
	/* SET'S ....................................... */
	/* ............................................. */

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre_productor(String nombre_productor) {
		this.nombre_productor = nombre_productor;
	}

	@Override
	public void setPK(Object id) {

		this.id = (Integer) id;
	}
}

/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import romaneo.unificado.entities.Acondicionador;
import romaneo.unificado.entities.Contacto;
import romaneo.unificado.entities.Persona;

import romaneo.utileria.Constantes;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Path(Constantes.ConstAcondicionador.PATH)
public class RestAcondicionador {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private List<Acondicionador> acondicionadores;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@GET
	@Path(Constantes.ConstAcondicionador.TODOS)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Acondicionador> getAcondicionadores() {
		generar();
		return acondicionadores;
	}

	@GET
	@Path(Constantes.ConstAcondicionador.NOMBRE)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Acondicionador> getAcondicionadores(@QueryParam("nombre") String nombre) {
		generar();

		List<Acondicionador> resultado = new ArrayList<>();

		for (Acondicionador acondicionador : acondicionadores)
			if (acondicionador.getPersona().getNombre().equals(nombre))
				resultado.add(acondicionador);

		return resultado;
	}

	@GET
	@Path(Constantes.ConstAcondicionador.DNI)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Acondicionador> getAcondicionadores(@QueryParam(Constantes.ConstAcondicionador.DNI) long dni) {
		generar();

		List<Acondicionador> resultado = new ArrayList<>();

		for (Acondicionador acondicionador : acondicionadores)
			if (acondicionador.getPersona().getDocumento() == dni)
				resultado.add(acondicionador);

		return resultado;
	}

	private void generar() {

		for (int i = 0; i < 10; i++) {
			Contacto contacto = new Contacto("email" + i + "@hotmail.com", "456486" + i);
			Persona persona = new Persona("Nombre " + i, "Apellido " + i, i);

			Acondicionador acondicionador = new Acondicionador(persona, contacto);

			acondicionadores.add(acondicionador);
		}
	}
}

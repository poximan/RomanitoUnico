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

import romaneo.unificado.domain.Acondicionador;
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

		return acondicionadores;
	}

	@GET
	@Path(Constantes.ConstAcondicionador.NOMBRE)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Acondicionador> getAcondicionadores(@QueryParam("nombre") String nombre) {

		List<Acondicionador> resultado = new ArrayList<>();

		for (Acondicionador acondicionador : acondicionadores)
			if (acondicionador.getNombre().equals(nombre))
				resultado.add(acondicionador);

		return resultado;
	}

	@GET
	@Path(Constantes.ConstAcondicionador.DNI)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Acondicionador> getAcondicionadores(@QueryParam(Constantes.ConstAcondicionador.DNI) long dni) {

		List<Acondicionador> resultado = new ArrayList<>();

		for (Acondicionador acondicionador : acondicionadores)
			if (acondicionador.getDni() == dni)
				resultado.add(acondicionador);

		return resultado;
	}
}

/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.services.acondicionador.AcondicionadorService;
import romaneo.utileria.Constantes;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

@Path(Constantes.ConstAcondicionador.PATH)
public class RestServiceAcondicionador extends BaseRest {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@SuppressWarnings("unchecked")
	@GET
	@Path(Constantes.ConstAcondicionador.TODOS)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Acondicionador> getAcondicionadores() {
		return getService().findAll();
	}

	@Override
	public String getClassName() {
		return Acondicionador.class.getName();
	}

	@Override
	protected String getEntityService() {
		return AcondicionadorService.class.getSimpleName();
	}
}

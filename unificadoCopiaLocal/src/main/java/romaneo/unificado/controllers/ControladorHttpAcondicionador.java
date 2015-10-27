/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.controllers;

import java.util.List;

import romaneo.unificado.entities.Acondicionador;
import romaneo.unificado.services.JSONParser;
import romaneo.utileria.HttpHelper;
import romaneo.utileria.Constantes;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

public class ControladorHttpAcondicionador {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private HttpHelper helper;

	private JSONParser<Acondicionador> parser;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public ControladorHttpAcondicionador() {

		helper = new HttpHelper(Constantes.URL_COMPLETA + Constantes.ConstAcondicionador.PATH);
		parser = new JSONParser<>(Acondicionador.class);
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	public List<Acondicionador> getAcondicionadores() {
		String res = helper.doGet(Constantes.ConstAcondicionador.TODOS);

		return parser.parseArray(res);
	}

	public List<Acondicionador> getAcondicionadores(String nombre) {
		String res = helper.doGet(Constantes.ConstAcondicionador.NOMBRE + "?nombre=" + nombre);

		return parser.parseArray(res);
	}

	public List<Acondicionador> getAcodicionadores(long dni) {
		String res = helper.doGet(Constantes.ConstAcondicionador.DNI + "?dni=" + dni);

		return parser.parseArray(res);
	}
}

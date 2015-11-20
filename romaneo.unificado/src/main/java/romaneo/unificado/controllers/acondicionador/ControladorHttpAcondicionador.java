package romaneo.unificado.controllers.acondicionador;
/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

import java.util.List;

import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.services.JSONParserImple;
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

	private JSONParserImple<Acondicionador> parser;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public ControladorHttpAcondicionador() {

		helper = new HttpHelper(Constantes.URL_COMPLETA + Constantes.ConstAcondicionador.PATH);
		parser = new JSONParserImple<>(Acondicionador.class);
	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	public List<Acondicionador> getAcondicionadores() {
		String res = helper.doGet(Constantes.ConstAcondicionador.TODOS);

		return parser.parseArray(res);
	}
}

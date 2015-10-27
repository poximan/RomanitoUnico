/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.utileria;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

public class Constantes {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	public static final String URL_SERVER = "http://localhost";

	public static final String PUERTO = "8080";

	public static final String NOMBRE_APP = "romanito";

	public static final String SERVICIOS = "rest";

	public static final String URL_COMPLETA = URL_SERVER + ":" + PUERTO + "/" + NOMBRE_APP + "/" + SERVICIOS + "/";

	public static class ConstAcondicionador {

		public static final String PATH = "acondicionadores/";

		public static final String TODOS = "todos";
		public static final String DNI = "dni";
		public static final String NOMBRE = "nombre";
	}
}

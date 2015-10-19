/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.daos;

import java.util.List;

/* ............................................. */
/* ............................................. */
/* INTERFAZ .................................... */
/* ............................................. */

public interface ServABMC {

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	public <TipoClase> void baja(TipoClase t);

	public <TipoClase> TipoClase modificacion(TipoClase t);

	public <T> List<T> consultaTodos(Class<T> klass);

	public <TipoClase> TipoClase consultaPorNamedQuery(String consulta, Object... parametros);
}

/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.utileria.abstractFactory;

import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.domain.BaseEntity;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

public class EntityFactory extends FabricaAbstracta {

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

	@Override
	public BaseEntity getEntity(Object objeto) {

		if (objeto instanceof Acondicionador)
			return (Acondicionador) objeto;

		return (Acondicionador) objeto;
	}
}
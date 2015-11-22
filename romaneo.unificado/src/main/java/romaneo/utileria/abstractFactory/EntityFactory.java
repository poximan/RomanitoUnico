/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.utileria.abstractFactory;

import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.domain.BaseEntity;
import romaneo.unificado.domain.Contratista;
import romaneo.unificado.domain.Establecimiento;
import romaneo.unificado.domain.Productor;

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

		if (objeto instanceof Productor)
			return (Productor) objeto;

		if (objeto instanceof Establecimiento)
			return (Establecimiento) objeto;

		if (objeto instanceof Contratista)
			return (Contratista) objeto;

		return null;
	}
}
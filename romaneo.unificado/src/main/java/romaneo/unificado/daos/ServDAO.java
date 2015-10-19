/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

public abstract class ServDAO implements ServABMC {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public ServDAO() {

	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public <TipoClase> void baja(TipoClase t) {

		em.remove(em.merge(t));
		em.flush();
	}

	@Override
	public <TipoClase> TipoClase modificacion(TipoClase t) {

		TipoClase nueva_entidad = null;
		nueva_entidad = em.merge(t);
		return nueva_entidad;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> consultaTodos(Class<T> klass) {
		return em.createQuery("Select t from " + klass.getSimpleName() + " t").getResultList();
	}

	@Override
	public <TipoClase> TipoClase consultaPorNamedQuery(String consulta, Object... parametros) {

		Query consulta_obj = this.em.createNamedQuery(consulta);

		for (Object obj : parametros)
			consulta_obj.setParameter(1, obj);

		@SuppressWarnings("unchecked")
		List<TipoClase> resultado = consulta_obj.getResultList();
		System.out.println("results is " + resultado.size());

		TipoClase resultado_unico = null;

		if (!resultado.isEmpty())
			resultado_unico = resultado.get(0);

		return resultado_unico;
	}

}

package romaneo.unificado.daos;

import romaneo.unificado.domain.Productor;

/** @author Eric Hidalgo */
public interface ProductorDao extends BaseDao<Productor, Integer> {

	/**
	 * Buscar por DNI
	 * 
	 * @param dni
	 *            DNI
	 * @return Chofer encontrado, caso contrario null
	 */
	Productor findByDni(Integer dni);
}
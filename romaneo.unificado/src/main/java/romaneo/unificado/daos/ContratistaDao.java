package romaneo.unificado.daos;

import romaneo.unificado.domain.Contratista;

/** @author Eric Hidalgo */
public interface ContratistaDao extends BaseDao<Contratista, Integer> {

	/**
	 * Buscar por DNI
	 * 
	 * @param dni
	 *            DNI
	 * @return Chofer encontrado, caso contrario null
	 */
	Contratista findByDni(Integer dni);
}
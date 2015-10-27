package romaneo.unificado.daos;

import romaneo.unificado.domain.Acondicionador;

/** @author Eric Hidalgo */
public interface AcondicionadorDao extends BaseDao<Acondicionador, Integer> {

	/**
	 * Buscar por DNI
	 * 
	 * @param dni
	 *            DNI
	 * @return Chofer encontrado, caso contrario null
	 */
	Acondicionador findByDni(Integer dni);

}
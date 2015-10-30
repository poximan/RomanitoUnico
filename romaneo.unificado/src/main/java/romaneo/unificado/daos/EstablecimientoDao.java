package romaneo.unificado.daos;

import romaneo.unificado.domain.Establecimiento;

/** @author Eric Hidalgo */
public interface EstablecimientoDao extends BaseDao<Establecimiento, Integer> {

	/**
	 * Buscar por DNI
	 * 
	 * @param dni
	 *            DNI
	 * @return Chofer encontrado, caso contrario null
	 */
	Establecimiento findByDni(Integer dni);

}
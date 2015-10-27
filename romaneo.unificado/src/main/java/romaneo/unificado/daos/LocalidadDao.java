package romaneo.unificado.daos;

import java.util.List;

import romaneo.unificado.domain.Localidad;
import romaneo.unificado.exceptions.NotUniqueResultException;

public interface LocalidadDao extends BaseDao<Localidad, String> {

	/**
	 * Buscar por nombre
	 * 
	 * @param name
	 *            Nombre de la localidad
	 * @return Lista de localidades
	 */
	List<Localidad> findByName(String name);

	/**
	 * Buscar por nombre
	 * 
	 * @param name
	 *            Nombre de la localidad
	 * @param state
	 *            Nombre de la provincia/estado
	 * @throws NotUniqueResultException
	 *             Los datos que ingresados no son suficientes para encontrar un
	 *             solo resultado.
	 * @return
	 */
	Localidad findByNameAndState(String name, String state) throws NotUniqueResultException;

}
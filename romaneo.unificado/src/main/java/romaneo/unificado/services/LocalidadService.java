package romaneo.unificado.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.daos.LocalidadDao;
import romaneo.unificado.domain.Localidad;
import romaneo.unificado.exceptions.NotUniqueResultException;

/**
 * Logica de negocios de localidades
 * 
 * @author ehidalgo
 */
public interface LocalidadService extends BaseService<Localidad, LocalidadDao> {

	/**
	 * Buscar por nombre
	 * 
	 * @param name
	 *            Nombre de la localidad
	 * @return Lista de localidades
	 */
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
	Localidad findByNameAndState(String name, String state) throws NotUniqueResultException;

}

package romaneo.unificado.daos;

import romaneo.unificado.domain.AdUser;

/** @author Eric Hidalgo */
public interface AdUserDao extends BaseDao<AdUser, Integer> {

	/**
	 * Busca un usuario por su nombre
	 * 
	 * @param name
	 *            Nombre que identifica al usuario
	 * @return El usuario buscado, si no la encuentra retorna null
	 */
	AdUser findByName(String name);

}
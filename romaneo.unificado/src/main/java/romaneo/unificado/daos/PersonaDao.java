package romaneo.unificado.daos;

import java.util.List;

import romaneo.unificado.domain.Persona;

/** @author Eric Hidalgo */
public interface PersonaDao extends BaseDao<Persona, Integer> {

	/**
	 * Busca un usuario por su nombre
	 * 
	 * @param name
	 *            Nombre que identifica al usuario
	 * @return El usuario buscado, si no la encuentra retorna null
	 */
	List<Persona> findByName(String name);

}
package romaneo.unificado.daos;

import romaneo.unificado.domain.Usuario;

/** @author Eric Hidalgo */
public interface UsuarioDao extends BaseDao<Usuario, Integer> {

	/**
	 * Busca un usuario por su nombre
	 * 
	 * @param name
	 *            Nombre que identifica al usuario
	 * @return El usuario buscado, si no la encuentra retorna null
	 */
	Usuario findByName(String name);

}
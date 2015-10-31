package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import romaneo.unificado.domain.Usuario;

/**
 * Clase Dao que permirte hacer CRUD (Create/Read/Update/Delete). Extiende de
 * HibernateDaoSupport.
 * 
 * @author Eric Hidalgo
 */
public class UsuarioDaoImple extends BaseDaoImple<Usuario, Integer> implements UsuarioDao {

	@Override
	protected Class<Usuario> getEntityClass() {
		return Usuario.class;
	}

	@Override
	public Usuario findByName(String nombre) {

		StringBuffer query = new StringBuffer("");
		query.append("FROM " + Usuario.class.getSimpleName() + " u ");
		query.append("WHERE 1 = 1 ");
		query.append("AND u.nombre = :nombre");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", nombre);

		List<Usuario> result = findQueryByParameters(query.toString(), parameters);
		return result.isEmpty() ? null : result.get(0);
	}
}
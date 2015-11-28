package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import romaneo.unificado.domain.Estado;

/**
 * Clase Dao que permirte hacer CRUD (Create/Read/Update/Delete). Extiende de
 * HibernateDaoSupport.
 * 
 * @author Eric Hidalgo
 */
public class EstadoDaoImple extends BaseDaoImple<Estado, Integer>implements EstadoDao {

	@Override
	protected Class<Estado> getEntityClass() {
		return Estado.class;
	}

	@Override
	public Estado getEstado(String nombre) {

		StringBuffer query = new StringBuffer("");
		query.append("FROM " + Estado.class.getSimpleName() + " u ");
		query.append("WHERE 1 = 1 ");
		query.append("AND u.nombre = :nombre");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", nombre);

		List<Estado> result = findQueryByParameters(query.toString(), parameters);
		return result.isEmpty() ? null : result.get(0);
	}
}
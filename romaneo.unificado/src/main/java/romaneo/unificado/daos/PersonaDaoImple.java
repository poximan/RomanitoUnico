package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import romaneo.unificado.domain.Persona;

/**
 * Clase Dao que permirte hacer CRUD (Create/Read/Update/Delete). Extiende de
 * HibernateDaoSupport.
 * 
 * @author Eric Hidalgo
 */
public class PersonaDaoImple extends BaseDaoImple<Persona, Integer> implements PersonaDao {

	@Override
	protected Class<Persona> getEntityClass() {
		return Persona.class;
	}

	@Override
	public List<Persona> findByName(String nombre) {

		// Consulta
		StringBuffer query = new StringBuffer("");
		query.append("FROM " + Persona.class.getSimpleName() + " u ");
		query.append("WHERE 1 = 1 ");
		query.append("AND u.nombre LIKE :nombre");

		// Parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", "%" + nombre.trim() + "%");

		return findQueryByParameters(query.toString(), parameters);
	}
}
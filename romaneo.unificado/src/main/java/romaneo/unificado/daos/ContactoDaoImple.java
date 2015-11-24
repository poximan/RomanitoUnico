package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import romaneo.unificado.domain.Contacto;
import romaneo.unificado.domain.Persona;

/**
 * Clase Dao que permirte hacer CRUD (Create/Read/Update/Delete). Extiende de
 * HibernateDaoSupport.
 * 
 * @author Eric Hidalgo
 */
public class ContactoDaoImple extends BaseDaoImple<Contacto, Integer> implements ContactoDao {

	@Override
	protected Class<Contacto> getEntityClass() {
		return Contacto.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> parameters) {

		StringBuffer query = new StringBuffer();
		query.append("SELECT e FROM " + Contacto.class.getSimpleName() + " e WHERE 1 = 1 ");

		Map<String, Object> queryParameters = new HashMap<String, Object>();

		if (parameters != null) {
			for (String filterKey : parameters.keySet()) {
				if (filterKey.equalsIgnoreCase(Contacto.Filters.BY_PERSONA.getValue())) {
					query.append("AND e.persona = :persona ");
					queryParameters.put("persona", parameters.get(filterKey));
				}
			}
		}

		// Crear las consultas
		Query hQuery = getSession().createQuery(query.toString());
		Query hQueryCount = getSession().createQuery(query.toString().replace("SELECT e FROM", "SELECT count(*) FROM"));

		// Agrego los parametros
		for (String key : queryParameters.keySet()) {
			hQuery.setParameter(key, queryParameters.get(key));
			hQueryCount.setParameter(key, queryParameters.get(key));
		}

		// Paginar
		if (page != null && pageSize != null) {
			hQuery.setFirstResult(page * pageSize);
			hQuery.setMaxResults(pageSize);
		}

		// Armar respuesta
		PagedQueryResponse response = new PagedQueryResponse();
		response.setResult(hQuery.list());
		Long count = (Long) hQueryCount.uniqueResult();
		response.setCount(count.intValue());

		return response;
	}

	@Override
	public Contacto findByEmail(String email) {

		StringBuffer query = new StringBuffer("");
		query.append("FROM " + Contacto.class.getSimpleName() + " u ");
		query.append("WHERE 1 = 1 ");
		query.append("AND u.email = :email");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("email", email);

		List<Contacto> result = findQueryByParameters(query.toString(), parameters);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public Contacto findByTelefono(String telefono) {

		StringBuffer query = new StringBuffer("");
		query.append("FROM " + Contacto.class.getSimpleName() + " u ");
		query.append("WHERE 1 = 1 ");
		query.append("AND u.telefono = :telefono");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("telefono", telefono);

		List<Contacto> result = findQueryByParameters(query.toString(), parameters);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public Contacto findByDireccion(String direccion) {

		StringBuffer query = new StringBuffer("");
		query.append("FROM " + Contacto.class.getSimpleName() + " u ");
		query.append("WHERE 1 = 1 ");
		query.append("AND u.direccion = :direccion");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("direccion", direccion);

		List<Contacto> result = findQueryByParameters(query.toString(), parameters);
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public List<Contacto> findByPersona(Persona persona) {

		StringBuffer query = new StringBuffer("");
		query.append("FROM " + Contacto.class.getSimpleName() + " u ");
		query.append("WHERE 1 = 1 ");
		query.append("AND u.persona = :persona");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("persona", persona);

		List<Contacto> result = findQueryByParameters(query.toString(), parameters);
		return result;
	}
}
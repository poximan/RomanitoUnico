package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import romaneo.unificado.domain.Establecimiento;

/**
 * Clase Dao que permirte hacer CRUD (Create/Read/Update/Delete). Extiende de
 * HibernateDaoSupport.
 * 
 * @author Eric Hidalgo
 */
public class EstablecimientoDaoImple extends BaseDaoImple<Establecimiento, Integer> implements EstablecimientoDao {

	@Override
	protected Class<Establecimiento> getEntityClass() {
		return Establecimiento.class;
	}

	@Override
	public Establecimiento findByDni(Integer dni) {

		String query = "FROM " + Establecimiento.class.getName() + " d WHERE d.dni = :dni";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("dni", dni);

		List<Establecimiento> list = findQueryByParameters(query, parameters);
		return list.isEmpty() ? null : list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> parameters) {

		StringBuffer query = new StringBuffer();
		query.append("SELECT e FROM " + Establecimiento.class.getSimpleName() + " e WHERE 1 = 1 ");

		Map<String, Object> queryParameters = new HashMap<String, Object>();

		if (parameters != null) {
			for (String filterKey : parameters.keySet()) {
				if (filterKey.equalsIgnoreCase(Establecimiento.Filters.BY_NOMBRE.getValue())) {
					query.append("AND UPPER(e.nombre) LIKE :nombre ");
					queryParameters.put("nombre",
							"%" + ((String) parameters.get(filterKey)).trim().toUpperCase() + "%");
				}
			}
		}
		// TODO: Ordenar la consulta

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

}
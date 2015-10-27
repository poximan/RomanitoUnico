package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import romaneo.unificado.domain.Interface;

/**
 * Clase Dao que permirte hacer CRUD (Create/Read/Update/Delete).
 * 
 * @author Eric Hidalgo
 */
public class InterfaceDaoImple extends BaseDaoImple<Interface, Integer> implements InterfaceDao {

	@Override
	protected Class<Interface> getEntityClass() {
		return Interface.class;
	}

	public Interface findByFilename(String filename, String type) {
		String query = " FROM " + Interface.class.getSimpleName()
				+ " i WHERE upper(i.filename) = :filename AND i.type = :type";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("filename", filename.toString().trim().toUpperCase());
		parameters.put("type", type);

		List<Interface> list = doQueryByParameters(query, parameters);
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<Interface> findAll(String type) throws DataAccessException {
		String query = "SELECT i FROM " + Interface.class.getSimpleName()
				+ " i INNER JOIN i.distributionCenters dc WHERE dc = :distributionCenter AND i.type = :type ORDER BY i.uploadDate DESC";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("type", type);

		return doQueryByParameters(query, parameters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> parameters) {

		StringBuffer query = new StringBuffer();
		query.append("SELECT e FROM " + Interface.class.getSimpleName() + " e ");
		
		Map<String, Object> queryParameters = new HashMap<String, Object>();

		if (parameters != null) {
			for (String filterKey : parameters.keySet()) {

				if (filterKey.equalsIgnoreCase(Interface.Filters.BY_FILENAME.getValue())) {
					query.append("AND upper(e.filename) LIKE :filename ");
					queryParameters.put("filename",
							"%" + parameters.get(filterKey).toString().trim().toUpperCase() + "%");
				}

				if (filterKey.equalsIgnoreCase(Interface.Filters.BY_TYPE.getValue())) {
					query.append("AND e.type = :type ");
					queryParameters.put("type", parameters.get(filterKey));
				}

				if (filterKey.equalsIgnoreCase(Interface.Filters.BY_DISTRIBUTION_CENTER.getValue())) {
					query.append("AND dc = :distributionCenter ");
					queryParameters.put("distributionCenter", parameters.get(filterKey));
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

	@Override
	public List<Interface> findUnprocessedAndAutoupload(String type) {
		String query = " FROM Interface i " + "WHERE i.processStopDate IS NULL " + "AND i.processStartDate IS NULL "
				+ "AND i.type = :type " + "AND i.autoupload IS TRUE ";

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("type", type);

		return doQueryByParameters(query, parameters);
	}
}
package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;

import romaneo.unificado.domain.MessageType;

public class MessageTypeDaoImple extends BaseDaoImple<MessageType, Integer> implements MessageTypeDao {

	@Override
	protected Class<MessageType> getEntityClass() {
		return MessageType.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> parameters) {

		StringBuffer query = new StringBuffer();
		query.append("FROM " + MessageType.class.getSimpleName() + " e WHERE 1 = 1 ");
		Map<String, Object> queryParameters = new HashMap<String, Object>();

		// Crear las consultas
		Query hQuery = getSession().createQuery(query.toString());
		Query hQueryCount = getSession().createQuery(query.toString().replace("FROM", "SELECT count(*) FROM"));

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

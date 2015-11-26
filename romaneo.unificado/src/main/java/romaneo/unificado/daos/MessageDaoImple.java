package romaneo.unificado.daos;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import romaneo.unificado.domain.Message;

public class MessageDaoImple extends BaseDaoImple<Message, Integer> implements MessageDao {

	@Override
	protected Class<Message> getEntityClass() {
		return Message.class;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> parameters) {
		StringBuffer query = new StringBuffer();
		Map<String, Object> queryParameters = new HashMap<String, Object>();

		query.append("SELECT distinct e FROM " + Message.class.getName() + " WHERE 1 = 1 ");

		if (parameters != null) {
			for (Iterator<String> it = parameters.keySet().iterator(); it.hasNext();) {

				String filterProperty = it.next();
				Object filterValue = parameters.get(filterProperty);

				if (filterProperty.equalsIgnoreCase(Message.Filters.BY_FROM_DATE.getValue())) {
					query.append(" AND e.created >= :dateFrom ");
					queryParameters.put("dateFrom", filterValue);
				}

				if (filterProperty.equalsIgnoreCase(Message.Filters.BY_TO_DATE.getValue())) {
					query.append(" AND e.created <= :dateTo ");
					queryParameters.put("dateTo", filterValue);
				}

				if (filterProperty.equalsIgnoreCase(Message.Filters.BY_MESSAGE_TYPE.getValue())) {
					query.append(" AND e.messageType = :messageType ");
					queryParameters.put("messageType", filterValue);
				}

				if (filterProperty.equalsIgnoreCase(Message.Filters.BY_UNPROCESSED.getValue())) {
					if ((Boolean) filterValue) {
						query.append(" AND e.processed IS NULL");
					}
				}
			}
		}

		// Crear las consultas
		Query hQuery = getSession().createQuery(query.toString());
		Query hQueryCount = getSession()
				.createQuery(query.toString().replace("SELECT distinct e FROM", "SELECT count(distinct e) FROM"));
		// Agrego los parametros
		for (String key : queryParameters.keySet()) {
			if (queryParameters.get(key) instanceof List) {
				hQuery.setParameterList(key, (List) queryParameters.get(key));
				hQueryCount.setParameterList(key, (List) queryParameters.get(key));
			} else {
				hQuery.setParameter(key, queryParameters.get(key));
				hQueryCount.setParameter(key, queryParameters.get(key));
			}
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
	public Integer countUnprocessedTheLastDays(Integer numberOfDays) {

		StringBuffer query = new StringBuffer(
				"SELECT COUNT(DISTINCT e.id) FROM " + Message.class.getSimpleName() + " e ");
		query.append("WHERE 1 = 1 ");
		query.append("AND e.travel.distributionCenter = :distributionCenter ");
		query.append("AND e.created >= :dateFrom ");
		query.append("AND e.created <= :dateTo ");
		query.append("AND e.processed IS NULL ");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, numberOfDays < 0 ? numberOfDays : (numberOfDays * (-1)));

		Query hQuery = getSession().createQuery(query.toString());
		hQuery.setParameter("dateFrom", calendar.getTime());
		hQuery.setParameter("dateTo", new Date());

		Long count = (Long) hQuery.uniqueResult();

		return count.intValue();
	}

}
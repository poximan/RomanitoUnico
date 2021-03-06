package romaneo.unificado.daos;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
		query.append("SELECT distinct e FROM " + Message.class.getSimpleName() + " e WHERE 1 = 1 ");

		Map<String, Object> queryParameters = new HashMap<String, Object>();

		if (parameters != null) {
			for (String filterKey : parameters.keySet()) {

				Object filterValue = parameters.get(filterKey);

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_DESDE.getValue())) {
					query.append(" AND e.fechaCreado >= :desde ");
					queryParameters.put("desde", filterValue);
				}

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_HASTA.getValue())) {
					query.append(" AND e.fechaCreado <= :hasta ");
					queryParameters.put("hasta", filterValue);
				}

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_DESTINATADIO.getValue())) {
					
					query.append(" AND ((e.usuario.persona.nombre LIKE :nombre) OR (e.usuario.persona.apellido LIKE :apellido)) ");
					
					queryParameters.put("nombre", "%" + filterValue + "%");
					queryParameters.put("apellido", "%" + filterValue + "%");
				}

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_ASUNTO.getValue())) {
					query.append(" AND e.asunto LIKE :asunto ");
					queryParameters.put("asunto", "%" + filterValue + "%");
				}

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_ESTADO.getValue())) {
					query.append(" AND e.estado.nombre = :estado ");
					queryParameters.put("estado", filterValue);
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
		query.append("AND e.fechaCreado >= :fechaCreado ");
		query.append("AND e.fechaCreado <= :fechaCreado ");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, numberOfDays < 0 ? numberOfDays : (numberOfDays * (-1)));

		Query hQuery = getSession().createQuery(query.toString());
		hQuery.setParameter("fechaCreado", calendar.getTime());
		hQuery.setParameter("fechaCreado", new Date());

		Long count = (Long) hQuery.uniqueResult();

		return count.intValue();
	}

	/*
	 * hay que implementar que coincida con los imei
	 * (non-Javadoc)
	 * @see romaneo.unificado.daos.MessageDao#mensajesImei(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Message> mensajesImei(String nombreUsuario, String imei)
	{
		StringBuffer query = new StringBuffer(
				"SELECT m FROM " + Message.class.getSimpleName() + " m ");
		query.append("WHERE m.usuario.nombreUsuario = :nombreUsuario");
		query.append(" AND m.fechaEnviado is null ");
	

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombreUsuario", nombreUsuario);

		List<Message> result = findQueryByParameters(query.toString(), parameters);
		findQueryByParameters(query.toString(), parameters);
		 
		return result;
	}


}
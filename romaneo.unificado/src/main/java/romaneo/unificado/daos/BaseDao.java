package romaneo.unificado.daos;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface BaseDao<Entity, Id> {

	public static final String ASC = "asc";
	public static final String DESC = "desc";

	void create(Entity object);

	void update(Entity object);

	void delete(Entity object);

	int count();

	Entity findById(Id id);

	List<Entity> findAll();

	List<Entity> findAll(String orderBy);

	List<Entity> findQuery(String query);

	List<Entity> findQueryByParameters(String query, Map<String, Object> parameters);

	Session getSession();

	void closeNewSession();

	PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, Map<String, Object> parameters);

	PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> parameters);
}

package romaneo.unificado.services;

import java.util.List;
import java.util.Map;

import romaneo.unificado.daos.BaseDao;
import romaneo.unificado.daos.PagedQueryResponse;
import romaneo.unificado.domain.BaseEntity;
import romaneo.unificado.exceptions.ValidationException;

@SuppressWarnings("rawtypes")
public interface BaseService<Entity extends BaseEntity, Dao extends BaseDao> {

	Entity find(Object key);

	void create(Entity entity) throws ValidationException;

	void delete(Entity entity);

	void update(Entity entity) throws ValidationException;

	Integer count();

	List<Entity> findAll();

	List<Entity> findAll(String orderBy);

	List<Entity> findQuery(String query);

	List<Entity> findQueryByParameters(String query, Map<String, Object> parameters);

	PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, Map<String, Object> parameters);

	PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> parameters);
}

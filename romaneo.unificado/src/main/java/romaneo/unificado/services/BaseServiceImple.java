package romaneo.unificado.services;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.daos.BaseDao;
import romaneo.unificado.daos.PagedQueryResponse;
import romaneo.unificado.domain.BaseEntity;

@SuppressWarnings("rawtypes")
public abstract class BaseServiceImple<Entity extends BaseEntity, Dao extends BaseDao>
		implements BaseService<Entity, Dao> {

	protected Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Entity find(Object key) {
		return (Entity) dao.findById(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Entity entity) {
		dao.create(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(Entity entity) {
		dao.update(entity);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Entity entity) {
		dao.delete(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer count() {
		return dao.count();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Entity> findAll() {
		return dao.findAll();
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Entity> findAll(String orderBy) {
		return dao.findAll(orderBy);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Entity> findQuery(String query) {
		return dao.findQuery(query);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Entity> findQueryByParameters(String query, Map<String, Object> parameters) {
		return dao.findQueryByParameters(query, parameters);
	}

	@SuppressWarnings("unchecked")
	public PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, Map<String, Object> parameters) {
		return dao.pagedFindAll(page, pageSize, parameters);
	}

	@SuppressWarnings("unchecked")
	public PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> parameters) {
		return dao.pagedFindAll(page, pageSize, sortField, sortOrder, parameters);
	}

}

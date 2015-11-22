package romaneo.unificado.daos;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseDaoImple<Entity extends Serializable, Id extends Serializable>
		implements BaseDao<Entity, Id> {

	protected Class<Entity> entity = getEntityClass();

	protected static final Logger logger = Logger.getLogger(BaseDao.class);

	private Boolean newSession = false;

	@Resource
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Metodo a implemetar en cada Dao. Resuelve el nombre de la clase entidad a
	 * la que hace referencia
	 * 
	 * @return El nombre de la clase entidad
	 */
	protected abstract Class<Entity> getEntityClass();

	/**
	 * Persiste un objeto en la BD
	 * 
	 * @param object
	 *            Objeto a persistir
	 * @throws DataAccessException
	 */
	@Override
	public void create(Entity object) throws DataAccessException {
		logger.info("Crear " + entity.getCanonicalName());
		sessionFactory.getCurrentSession().persist(object);
		logger.info("Creado-> " + entity.getCanonicalName());
	}

	/**
	 * Actualiza un objeto en la BD
	 * 
	 * @param object
	 *            Objeto a persistir
	 * @throws DataAccessException
	 */
	@Override
	public void update(Entity object) throws DataAccessException {
		logger.info("Actualizar " + entity.getCanonicalName());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().update(object);
		logger.info("Actualizado-> " + entity.getCanonicalName());
	}

	/**
	 * Elimina un objeto en la BD
	 * 
	 * @param object
	 *            Objeto a persistir
	 * @throws DataAccessException
	 */
	@Override
	public void delete(Entity object) throws DataAccessException {
		logger.info("Eliminar-> " + entity.getCanonicalName());
		sessionFactory.getCurrentSession().delete(object);
		logger.info("Eliminado-> " + entity.getCanonicalName());
	}

	/**
	 * Cuenta cantidad de objetos de una entidad
	 * 
	 * @return La cantidad de objetos de una entidad
	 * @throws DataAccessException
	 */
	@Override
	public int count() throws DataAccessException {
		Integer count = (Integer) sessionFactory.getCurrentSession()
				.createQuery("SELECT count(*) FROM " + entity.getName() + " e").list().size();

		return count.intValue();
	}

	/**
	 * Busca un objeto por su Id
	 * 
	 * @param id
	 *            Id que identifica al objeto
	 * @return Objeto buscado, caso contrario devuelve Null
	 * @throws DataAccessException
	 *             Excepcion generica independiente del motor BD
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Entity findById(Id id) throws DataAccessException {
		logger.info("Buscar por ID-> " + entity.getCanonicalName());
		return (Entity) sessionFactory.getCurrentSession().get(entity.getName(), id);
	}

	/**
	 * Retorna una lista completa de todos los objetos de una entidad
	 * 
	 * @return Lista completa de objetos
	 * @throws DataAccessException
	 */
	@Override
	public List<Entity> findAll() throws DataAccessException {
		logger.info("Buscar todos-> " + entity.getCanonicalName());
		return findAll(null);
	}

	@SuppressWarnings({ "unchecked" })
	public List<Entity> findAll(String orderBy) throws DataAccessException {
		logger.info("Buscar todos (ordenado)-> " + entity.getCanonicalName());
		sessionFactory.getCurrentSession().flush();
		return sessionFactory.getCurrentSession().createQuery("FROM " + entity.getName() + " e"
				+ (orderBy != null && !"".equals(orderBy) ? " ORDER BY e." + orderBy : "")).list();
	}

	/**
	 * Retorna una lista de acuerdo a una consulta
	 * 
	 * @return Lista completa de objetos dependiente de la consulta realizada
	 * @throws DataAccessException
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<Entity> findQuery(String query) throws DataAccessException {
		if (getSession().isOpen())
			getSession().flush();
		return getSession().createQuery(query).list();
	}

	@Override
	public List<Entity> findQueryByParameters(String query, Map<String, Object> parameters) throws DataAccessException {
	
		if (parameters == null) {
			return findQuery(query);
		}

		logger.setLevel(Level.OFF);
		if (getSession().isOpen())
			getSession().flush();
		Query hQuery = getSession().createQuery(query);

		for (String key : parameters.keySet()) {
			hQuery.setParameter(key, parameters.get(key));
		}

		@SuppressWarnings("unchecked")
		List<Entity> list = hQuery.list();

		logger.info("Buscar por consulta personalizada");
		return list;
	}

	public Session getSession() {
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			Logger.getLogger(getClass()).warn("HibernateException");
			e.printStackTrace();
			Logger.getLogger(getClass()).warn("NUEVO OPEN SESSION");
			session = sessionFactory.openSession();
			newSession = true;
		}
		return session;
	}

	public void closeNewSession() {
		if (newSession) {
			getSession().close();
			newSession = false;
		}
	}

	@Override
	public PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, Map<String, Object> parameters) {
		return pagedFindAll(page, pageSize, null, null, parameters);
	}

	@Override
	public PagedQueryResponse pagedFindAll(Integer page, Integer pageSize, String sortField, String sortOrder,
			Map<String, Object> parameters) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Entity> doQueryByParameters(String query, Map<String, Object> parameters) {

		logger.setLevel(Level.OFF);

		if (parameters == null) {
			return findQuery(query);
		}

		if (getSession().isOpen())
			getSession().flush();
		Query hQuery = getSession().createQuery(query);

		for (String key : parameters.keySet()) {
			if (parameters.get(key) instanceof List)
				hQuery.setParameterList(key, (List) parameters.get(key));
			else
				hQuery.setParameter(key, parameters.get(key));
		}

		return hQuery.list();
	}

}
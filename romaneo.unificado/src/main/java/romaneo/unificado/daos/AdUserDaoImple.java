package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import romaneo.unificado.domain.AdUser;

/**
 * Clase Dao que permirte hacer CRUD (Create/Read/Update/Delete). Extiende de
 * HibernateDaoSupport.
 * 
 * @author Eric Hidalgo
 */
public class AdUserDaoImple extends BaseDaoImple<AdUser, Integer> implements AdUserDao {

	@Override
	protected Class<AdUser> getEntityClass() {
		return AdUser.class;
	}

	@Override
	public AdUser findByName(String name) {

		StringBuffer query = new StringBuffer("");
		query.append("FROM " + AdUser.class.getSimpleName() + " u ");
		query.append("WHERE 1 = 1 ");
		query.append("AND u.name = :name");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("name", name);

		List<AdUser> result = findQueryByParameters(query.toString(), parameters);
		return result.isEmpty() ? null : result.get(0);
	}

}
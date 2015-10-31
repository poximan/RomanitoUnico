package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import romaneo.unificado.domain.Localidad;
import romaneo.unificado.exceptions.NotUniqueResultException;

public class LocalidadDaoImple extends BaseDaoImple<Localidad, String> implements LocalidadDao {

	@Override
	protected Class<Localidad> getEntityClass() {
		return Localidad.class;
	}

	@Override
	public List<Localidad> findByName(String name) {

		// Consulta
		StringBuffer query = new StringBuffer("");
		query.append("FROM " + Localidad.class.getSimpleName() + " e ");
		query.append("WHERE 1 = 1 ");
		query.append("AND upper(e.nombre_localidad) LIKE :nombre_localidad");

		// Parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre_localidad", "%" + name.trim().toUpperCase() + "%");

		return doQueryByParameters(query.toString(), parameters);

	}

	@Override
	public Localidad findByNameAndState(String name, String state) throws NotUniqueResultException {

		// Consulta
		StringBuffer query = new StringBuffer("");
		query.append("FROM " + Localidad.class.getSimpleName() + " e ");
		query.append("WHERE 1 = 1 ");
		query.append("AND upper(e.localidad) like :nombre_localidad ");
		query.append("AND upper(e.localidad.partido.provincia.nombre_provincia) = :nombre_provincia ");

		// Parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre_localidad", "%" + name.trim().toUpperCase() + "%");
		parameters.put("nombre_provincia", state.trim().toUpperCase());

		List<Localidad> list = doQueryByParameters(query.toString(), parameters);
		if (list.size() > 1) {
			throw new NotUniqueResultException();
		}
		return list.isEmpty() ? null : list.get(0);
	}

}

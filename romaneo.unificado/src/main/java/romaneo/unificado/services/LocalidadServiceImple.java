package romaneo.unificado.services;

import java.util.List;

import romaneo.unificado.daos.LocalidadDao;
import romaneo.unificado.domain.Localidad;
import romaneo.unificado.exceptions.NotUniqueResultException;

public class LocalidadServiceImple extends BaseServiceImple<Localidad, LocalidadDao> implements LocalidadService {

	@Override
	public List<Localidad> findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public Localidad findByNameAndState(String name, String state) throws NotUniqueResultException {
		return dao.findByNameAndState(name, state);
	}

}

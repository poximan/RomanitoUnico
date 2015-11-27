package romaneo.unificado.services;

import java.util.List;

import romaneo.unificado.daos.PersonaDao;
import romaneo.unificado.domain.Persona;

/**
 * Servicio que integra el modelo propio de usuarios con Hibernate al de Spring
 * Security. Extiende de UserDetailsService para la conversión
 * 
 * @author Eric Hidalgo
 */
public class PersonaServiceImple extends BaseServiceImple<Persona, PersonaDao> implements PersonaService {

	@Override
	public List<Persona> findByName(String name) {

		return dao.findByName(name);
	}
}

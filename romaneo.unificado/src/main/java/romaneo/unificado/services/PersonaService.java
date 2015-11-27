package romaneo.unificado.services;

import java.util.List;

import romaneo.unificado.daos.PersonaDao;
import romaneo.unificado.domain.Persona;

/**
 * Logica de negocios de usuarios
 * 
 * @author ehidalgo
 */
public interface PersonaService extends BaseService<Persona, PersonaDao> {

	List<Persona> findByName(String name);
}
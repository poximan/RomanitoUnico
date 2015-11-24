package romaneo.unificado.daos;

import java.util.List;

import romaneo.unificado.domain.Contacto;
import romaneo.unificado.domain.Persona;

/**
 * @author Eric Hidalgo
 */
public interface ContactoDao extends BaseDao<Contacto, Integer> {

	Contacto findByEmail(String email);

	Contacto findByTelefono(String telefono);

	Contacto findByDireccion(String direccion);

	List<Contacto> findByPersona(Persona persona);
}
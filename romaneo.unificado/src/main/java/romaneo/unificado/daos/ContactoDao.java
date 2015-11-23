package romaneo.unificado.daos;

import romaneo.unificado.domain.Contacto;

/** @author Eric Hidalgo */
public interface ContactoDao extends BaseDao<Contacto, Integer> {

	Contacto findByEmail(String email);

	Contacto findByTelefono(String telefono);

	Contacto findByDireccion(String direccion);
}
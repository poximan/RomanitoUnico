package romaneo.unificado.services;

import romaneo.unificado.daos.EstadoDao;
import romaneo.unificado.domain.Estado;

/**
 * Servicio que integra el modelo propio de usuarios con Hibernate al de Spring
 * Security. Extiende de UserDetailsService para la conversión
 * 
 * @author Eric Hidalgo
 */
public class EstadoServiceImple extends BaseServiceImple<Estado, EstadoDao>implements EstadoService {

	@Override
	public Estado getEstado(String nombre) {

		return dao.getEstado(nombre);
	}
}

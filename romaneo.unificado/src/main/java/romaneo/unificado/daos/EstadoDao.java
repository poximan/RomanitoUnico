package romaneo.unificado.daos;

import romaneo.unificado.domain.Estado;

/**
 * @author Eric Hidalgo
 */
public interface EstadoDao extends BaseDao<Estado, Integer> {

	Estado getEstado(String nombre);
}
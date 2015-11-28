package romaneo.unificado.services;

import romaneo.unificado.daos.EstadoDao;
import romaneo.unificado.domain.Estado;

/**
 * Logica de negocios de usuarios
 * 
 * @author ehidalgo
 */
public interface EstadoService extends BaseService<Estado, EstadoDao> {

	Estado getEstado(String nombre);
}
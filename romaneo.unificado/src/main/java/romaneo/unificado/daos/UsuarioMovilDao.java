package romaneo.unificado.daos;


import java.util.List;

import romaneo.unificado.domain.UsuarioMovil;

public interface UsuarioMovilDao extends BaseDao<UsuarioMovil, Integer> 
{
	/*
	 * buscar por idUsuario y fechaFin == null
	 */
	public List<UsuarioMovil> findByIdUsuario(Integer idUsuario);
}

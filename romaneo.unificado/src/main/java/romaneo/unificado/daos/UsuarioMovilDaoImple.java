package romaneo.unificado.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import romaneo.unificado.domain.UsuarioMovil;

public class UsuarioMovilDaoImple extends BaseDaoImple<UsuarioMovil, Integer> implements UsuarioMovilDao 
{

	@Override
	protected Class<UsuarioMovil> getEntityClass()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioMovil> findByIdUsuario(Integer idUsuario)
	{
		StringBuffer query = new StringBuffer("");
		query.append("FROM " + UsuarioMovil.class.getSimpleName() + " um ");
		query.append("WHERE um.idUsuario= :idUsuario ");
		query.append("AND um.fechaFin= NULL");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idUsuario", idUsuario);

		List<UsuarioMovil> result = findQueryByParameters(query.toString(), parameters);
		
		return result;
	}

}

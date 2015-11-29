package romaneo.unificado.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import romaneo.unificado.daos.UsuarioMovilDao;
import romaneo.unificado.domain.UsuarioMovil;

public class UsuarioMovilServiceImple extends BaseServiceImple<UsuarioMovil, UsuarioMovilDao>
		implements UsuarioMovilService {

	@Override
	public UsuarioMovil findByNameIMEI(Integer idUsuario, String imei) {

		// Consulta
		StringBuffer query = new StringBuffer("");
		query.append("FROM " + UsuarioMovil.class.getSimpleName() + " um ");
		query.append("WHERE 1 = 1 ");
		query.append("AND um.idUsuario = :idUsuario");
		query.append("AND um.idMovil.IMEI = :idMovil");
		query.append("AND un.fechaFin=NULL");


		// Parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idUsuario", idUsuario);
		parameters.put("idMovil", imei);

		List<UsuarioMovil> result = findQueryByParameters(query.toString(), parameters);
		return result.isEmpty() ? null : result.get(0);
	}
}

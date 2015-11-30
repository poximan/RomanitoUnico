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
		query.append("WHERE um.idUsuario.id = :idUsuario ");
		query.append("AND um.idMovil.imei = :imei ");
		query.append("AND um.fechaFin is null");


		// Parametros
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idUsuario", idUsuario);
		parameters.put("imei", imei);

		List<UsuarioMovil> result = findQueryByParameters(query.toString(), parameters);
		System.out.println("resultTam: "+result.size());
		return result.size()==0 ? null : result.get(0);
	}
}

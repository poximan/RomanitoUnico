package romaneo.unificado.services;

import romaneo.unificado.daos.UsuarioMovilDao;
import romaneo.unificado.domain.UsuarioMovil;

public interface UsuarioMovilService extends BaseService<UsuarioMovil, UsuarioMovilDao> {

	UsuarioMovil findByNameIMEI(Integer idUsuario, String imei);
}

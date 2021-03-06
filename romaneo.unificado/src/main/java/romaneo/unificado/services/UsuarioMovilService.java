package romaneo.unificado.services;

import romaneo.unificado.daos.UsuarioMovilDao;
import romaneo.unificado.domain.UsuarioMovil;

public interface UsuarioMovilService extends BaseService<UsuarioMovil, UsuarioMovilDao> {

	UsuarioMovil findByNameIMEI(String nombreUsuario, String imei);
}

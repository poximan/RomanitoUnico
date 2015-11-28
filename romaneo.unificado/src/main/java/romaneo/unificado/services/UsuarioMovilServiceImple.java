package romaneo.unificado.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import romaneo.unificado.daos.UsuarioMovilDao;
import romaneo.unificado.domain.UsuarioMovil;

public class UsuarioMovilServiceImple extends BaseServiceImple<UsuarioMovil, UsuarioMovilDao>
		implements UserDetailsService, UsuarioMovilService
{

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException
	{
		// TODO Auto-generated method stub
		return null;
	}

}

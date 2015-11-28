package romaneo.unificado.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import romaneo.unificado.daos.UsuarioDao;
import romaneo.unificado.domain.Usuario;
import romaneo.unificado.domain.UsuarioRol;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ResourceError;
import romaneo.unificado.exceptions.ValidationException;

/**
 * Servicio que integra el modelo propio de usuarios con Hibernate al de Spring
 * Security. Extiende de UserDetailsService para la conversión
 * 
 * @author Eric Hidalgo
 */
public class UsuarioServiceImple extends BaseServiceImple<Usuario, UsuarioDao>
		implements UserDetailsService, UsuarioService {

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		Usuario user = dao.findByName(name);

		if (user == null)
			throw new UsernameNotFoundException("El usuario " + user + " no existe en la base de datos");

		// Convierto mi usuario a un UserDetails de SpringSecurity
		return new org.springframework.security.core.userdetails.User(user.getNombre_usuario(), user.getClave_usuario(),
				user.getActivo() == 'Y' ? true : false, true, true, true, convertProfiles(user.getRoles()));
	}

	/** Convierte los perfiles del usuario a los de Spring-Security */
	private Collection<GrantedAuthority> convertProfiles(List<UsuarioRol> roles) {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UsuarioRol role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getId_rol().getNombre()));
		}

		return authorities;
	}

	@Override
	public Usuario getLoguedUser() {
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			return dao.findByName(user.getUsername());
		} else
			return null;
	}

	@Override
	public void changePassword(String name, String oldPassword, String newPassword, String reNewPassword)
			throws UsernameNotFoundException {
		Usuario user = dao.findByName(name);

		if (user == null)
			throw new UsernameNotFoundException("El usuario " + user + " no existe en la base de datos");

		validationPasswords(user, oldPassword, newPassword, reNewPassword);

		user.setClave_usuario(newPassword);
		dao.update(user);
	}

	private void validationPasswords(Usuario user, String oldPassword, String newPassword, String reNewPassword)
			throws ValidationException {

		ResourceError error = new ResourceError();

		if (oldPassword == null || !oldPassword.equals(user.getClave_usuario()))
			error.addFieldError(new FieldResourceError("user", "password", null,
					"La contraseña ingresada no coincide con la actual"));

		if ((newPassword == null || newPassword.isEmpty() || newPassword.length() < 6)
				|| (reNewPassword == null || reNewPassword.isEmpty() || reNewPassword.length() < 6))
			error.addFieldError(new FieldResourceError("user", "password", null,
					"La nueva contraseña debe tener un largo no menor a 6 caracteres"));

		if (!newPassword.equals(reNewPassword))
			error.addFieldError(new FieldResourceError("user", "password", null, "Las contraseñas deben ser iguales"));

		if (error.isError())
			throw new ValidationException(error);

	}

	@Override
	public List<Usuario> findByLikeName(String nombre) {

		return dao.findByLikeName(nombre);
	}
}

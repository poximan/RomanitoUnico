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

import romaneo.unificado.daos.AdUserDao;
import romaneo.unificado.domain.AdUser;
import romaneo.unificado.domain.AdUserRoles;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ResourceError;
import romaneo.unificado.exceptions.ValidationException;

/**
 * Servicio que integra el modelo propio de usuarios con Hibernate al de Spring
 * Security. Extiende de UserDetailsService para la conversión
 * 
 * @author Eric Hidalgo
 */
public class AdUserServiceImple extends BaseServiceImple<AdUser, AdUserDao>
		implements UserDetailsService, AdUserService {

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		AdUser user = dao.findByName(name);

		if (user == null)
			throw new UsernameNotFoundException("El usuario " + user + " no existe en la base de datos");

		// Convierto mi usuario a un UserDetails de SpringSecurity
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				user.getIsactive() == 'Y' ? true : false, true, true, true, convertProfiles(user.getAdUserRolesList()));
	}

	/** Convierte los perfiles del usuario a los de Spring-Security */
	private Collection<GrantedAuthority> convertProfiles(List<AdUserRoles> roles) {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (AdUserRoles role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getAdRole().getName()));
		}

		return authorities;
	}

	@Override
	public AdUser getLoguedUser() {
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
		AdUser user = dao.findByName(name);

		if (user == null)
			throw new UsernameNotFoundException("El usuario " + user + " no existe en la base de datos");

		validationPasswords(user, oldPassword, newPassword, reNewPassword);

		user.setPassword(newPassword);
		dao.update(user);
	}

	private void validationPasswords(AdUser user, String oldPassword, String newPassword, String reNewPassword)
			throws ValidationException {

		ResourceError error = new ResourceError();

		if (oldPassword == null || !oldPassword.equals(user.getPassword()))
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

}

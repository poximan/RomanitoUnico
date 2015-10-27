package romaneo.unificado.services;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.daos.AdUserDao;
import romaneo.unificado.domain.AdUser;

/**
 * Logica de negocios de usuarios
 * 
 * @author ehidalgo
 */
public interface AdUserService extends BaseService<AdUser, AdUserDao> {

	/**
	 * Busca un usuario por su nombre
	 * 
	 * @param name
	 *            Nombre de usuario
	 * @return Usuario
	 * @throws UsernameNotFoundException
	 * @throws DataAccessException
	 */
	@Transactional(readOnly = true)
	UserDetails loadUserByUsername(String name) throws UsernameNotFoundException;

	/**
	 * Obtener el usuario autenticado
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	AdUser getLoguedUser();

	/**
	 * Cambiar contraseña
	 * 
	 * @param name
	 *            Nombre de usuario
	 * @param oldPassword
	 *            Contraseña vieja
	 * @param newPassword
	 *            Contraseña nueva
	 * @param reNewPassword
	 *            Repeticion de la nueva contraseña
	 * @throws UsernameNotFoundException
	 *             El usuario no existe
	 */
	@Transactional(rollbackFor = Exception.class)
	void changePassword(String name, String oldPassword, String newPassword, String reNewPassword)
			throws UsernameNotFoundException;

}
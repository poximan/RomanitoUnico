package romaneo.unificado.services;

import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.daos.ContactoDao;
import romaneo.unificado.domain.Contacto;
import romaneo.unificado.exceptions.ValidationException;

/**
 * Logica de negocios de usuarios
 * 
 * @author ehidalgo
 */
public interface ContactoService extends BaseService<Contacto, ContactoDao> {

	/**
	 * Validación
	 * 
	 * @param entity
	 *            Chofer
	 * @throws ValidationException
	 *             Errores de validacion
	 */
	void validate(Contacto entity) throws ValidationException;

	/**
	 * Método que guarda un nuevo chofer en la DB, previamente validado. En caso
	 * de fallar la validación arroja una RuntimeException especificando la
	 * causa.
	 * 
	 * @param entity
	 *            Chofer a persistir.
	 * @throws ValidationException
	 *             Errores de validación
	 */
	@Transactional(rollbackFor = Exception.class)
	void create(Contacto entity) throws ValidationException;

	/**
	 * Método que actualiza los datos de un chofer en la DB, previamente
	 * validado. En caso de fallar la validación arroja una RuntimeException
	 * especificando la causa.
	 * 
	 * @param entity
	 *            Chofer a actualizar.
	 * @throws ValidationException
	 *             Errores de validación
	 */
	@Transactional(rollbackFor = Exception.class)
	void update(Contacto entity) throws ValidationException;
}
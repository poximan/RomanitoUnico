package romaneo.unificado.services.establecimiento;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.daos.EstablecimientoDao;
import romaneo.unificado.domain.Establecimiento;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.BaseService;

public interface EstablecimientoService extends BaseService<Establecimiento, EstablecimientoDao> {

	/**
	 * Validación
	 * 
	 * @param entity
	 *            Chofer
	 * @throws ValidationException
	 *             Errores de validacion
	 */
	void validate(Establecimiento entity) throws ValidationException;

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
	void create(Establecimiento entity) throws ValidationException;

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
	void update(Establecimiento entity) throws ValidationException;

	/**
	 * Busca todos los choferes del centro de distribucion y a demas agrega una
	 * instancia que en los combobox de ZK permitira seleccionar todos.
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Establecimiento> getEstablecimientosForCombobox();

	/**
	 * Buscar por DNI
	 * 
	 * @param dni
	 *            DNI
	 * @return Chofer encontrado, caso contrario null
	 */
	@Transactional(readOnly = true)
	Establecimiento findByDni(Integer dni);

}
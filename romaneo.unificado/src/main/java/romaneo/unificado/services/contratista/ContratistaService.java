package romaneo.unificado.services.contratista;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.daos.ContratistaDao;
import romaneo.unificado.domain.Contratista;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.BaseService;

public interface ContratistaService extends BaseService<Contratista, ContratistaDao> {

	/**
	 * Validación
	 * 
	 * @param entity
	 *            Chofer
	 * @throws ValidationException
	 *             Errores de validacion
	 */
	void validate(Contratista entity) throws ValidationException;

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
	void create(Contratista entity) throws ValidationException;

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
	void update(Contratista entity) throws ValidationException;

	/**
	 * Busca todos los choferes del centro de distribucion y a demas agrega una
	 * instancia que en los combobox de ZK permitira seleccionar todos.
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	List<Contratista> getContratistasForCombobox();

	/**
	 * Buscar por DNI
	 * 
	 * @param dni
	 *            DNI
	 * @return Chofer encontrado, caso contrario null
	 */
	@Transactional(readOnly = true)
	Contratista findByDni(Integer dni);

}
package romaneo.unificado.daos;

import java.util.List;

import romaneo.unificado.domain.Interface;

/** @author ehidalgo */
public interface InterfaceDao extends BaseDao<Interface, Integer> {

	/**
	 * Buscar por nombre
	 * 
	 * @param filename
	 *            Nombre de la interface
	 * @param type
	 *            Tipo de interface
	 * @return
	 */
	Interface findByFilename(String filename, String type);

	/**
	 * Buscar las interfaces no procesadas que se subieron automaticamente del
	 * tipo especificado
	 * 
	 * @param type
	 *            Tipo de interfaces a buscar
	 * @return Lista de interfaces sin procesar
	 */
	List<Interface> findUnprocessedAndAutoupload(String type);

	/**
	 * Buscar todas interfaces segun tipo y centro de distribucion
	 * 
	 * @param type
	 *            Tipo de interface
	 * @return Lista de interfaces
	 */
	List<Interface> findAll(String type);

}
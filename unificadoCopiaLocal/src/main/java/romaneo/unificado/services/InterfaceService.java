package romaneo.unificado.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.daos.InterfaceDao;
import romaneo.unificado.domain.Interface;
import romaneo.unificado.exceptions.InterfaceAlreadyExistsException;

/**
 * Logica de negocios de interfaces
 * 
 * @author ehidalgo
 */
public interface InterfaceService extends BaseService<Interface, InterfaceDao> {

	/**
	 * Extractor que obtiene los datos de la linea de la interface
	 * 
	 * @param extractorLine
	 *            Extractor
	 */
	@SuppressWarnings("rawtypes")
	void setExtractorLine(InterfaceExtractorLineData extractorLine);

	/**
	 * Carpeta donde guardar las interfaces
	 * 
	 * @return
	 */
	String getFolder();

	/**
	 * Buscar por nombre
	 * 
	 * @param filename
	 *            Nombre de la interface
	 * @param type
	 *            Tipo de interface
	 * @return
	 */
	@Transactional(readOnly = true)
	Interface findByFilename(String filename, String type);

	/**
	 * Buscar las interfaces no procesadas que se subieron automaticamente del
	 * tipo especificado
	 * 
	 * @param type
	 *            Tipo de interfaces a buscar
	 * @return Lista de interfaces sin procesar
	 */
	@Transactional(readOnly = true)
	List<Interface> findUnprocessedAndAutoupload(String type);

	/**
	 * Buscar todas interfaces segun tipo y centro de distribucion
	 * 
	 * @param type
	 *            Tipo de interface
	 * @return Lista de interfaces
	 */
	@Transactional(readOnly = true)
	List<Interface> findAll(String type);

	/**
	 * Crea la interface en la carpeta correspondiente y en la BD
	 * 
	 * @param dataFile
	 *            Archivo que representa la interface
	 * @param filename
	 *            Nombre de la interface
	 * @param autoaupload
	 *            Fue creada por el proceso automático
	 * @return Interface creada
	 */
	@Transactional(rollbackFor = Exception.class)
	Interface uploadFile(InputStream dataFile, String filename, Boolean autoaupload)
			throws InterfaceAlreadyExistsException, IOException;

	/**
	 * Procesar la interface Nota: En cada implementacion debe marcarse como
	 * transaccional.
	 * 
	 * @param interfaz
	 *            Interface a procesar
	 * @param distributionCenter
	 *            Centro de distribucion
	 * @param notifyByEmail
	 *            Notificar por correo electronico los resultados
	 * @param progress
	 *            Objeto que muestra el progreso del proceso al usuario
	 * @return Resultados obtenidos al procesar la interface
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	InterfaceResult processFile(Interface interfaz, Boolean notifyByEmail, ProgressListener progress) throws Exception;

	String getType();

	String getName();

	/**
	 * Enviar mail.
	 * 
	 * @param interfaz
	 *            Interfaz de la cual se enviaran los resultados de
	 *            procesamiento.
	 */
	void sendEmail(Interface interfaz);

}
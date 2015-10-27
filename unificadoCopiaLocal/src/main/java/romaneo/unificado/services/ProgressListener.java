package romaneo.unificado.services;

/**
 * Permite interactuar con la vista y mostrar el progreso del procesamiento de
 * las interfaces
 * 
 * @author ehidalgo
 */
public interface ProgressListener {

	/**
	 * Actualiza el progreso de procesamiento de las interfaces
	 * 
	 * @param currentLine
	 *            Linea actual
	 * @param totalLines
	 *            Lineas totales
	 * @param id
	 *            Id que identifica la interface que se esta procesando
	 * @param resource
	 */
	void updateProgress(Integer currentLine, Integer totalLines, Integer id, String resource);

}
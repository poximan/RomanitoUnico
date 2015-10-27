package romaneo.unificado.services;

import romaneo.unificado.exceptions.ValidationException;

/**
 * Extrae los datos a partir de la linea de la interface
 * 
 * @author ehidalgo
 */
public interface InterfaceExtractorLineData<D> {

	/**
	 * Recibe la linea proveniente de la interface y extrae los datos necesarios
	 * 
	 * @param data
	 *            Linea proveniente de la interface
	 */
	void extract(D row) throws ValidationException;

}
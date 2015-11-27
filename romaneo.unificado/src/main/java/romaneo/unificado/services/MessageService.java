package romaneo.unificado.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.daos.MessageDao;
import romaneo.unificado.domain.Message;
import romaneo.unificado.domain.Message.TipoMensaje;

public interface MessageService extends BaseService<Message, MessageDao> {

	/**
	 * Cuenta la cantidad de mensajes sin procesar de los últimos días
	 * 
	 * @param numberOfDays
	 *            Cantidad de días sin procesar
	 * @return Cantidad de mensajes sin procesar
	 */
	@Transactional(readOnly = true)
	Integer countUnprocessedTheLastDays(Integer numberOfDays);

	/**
	 * Marca un mensaje como procesado o leído, seteandole la fecha actual.
	 * 
	 * @param message
	 *            Mensaje a actualizar.
	 */
	@Transactional(rollbackFor = Exception.class)
	void markAsProcessed(Message message);

	@Transactional(readOnly = true)
	List<TipoMensaje> findByTipo(String value);
}
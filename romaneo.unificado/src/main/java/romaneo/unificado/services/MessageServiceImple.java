package romaneo.unificado.services;

import java.util.Calendar;
import java.util.List;

import romaneo.unificado.daos.MessageDao;
import romaneo.unificado.domain.Message;
import romaneo.unificado.domain.Message.TipoMensaje;

public class MessageServiceImple extends BaseServiceImple<Message, MessageDao> implements MessageService {

	@Override
	public Integer countUnprocessedTheLastDays(Integer numberOfDays) {
		return dao.countUnprocessedTheLastDays(numberOfDays);
	}

	@Override
	public void markAsProcessed(Message message) {
		Calendar now = Calendar.getInstance();
		message.setFecha_recibido_ack(now);
		update(message);
	}

	@Override
	public List<TipoMensaje> findByTipo(String value) {
		
		return null;
	}
}
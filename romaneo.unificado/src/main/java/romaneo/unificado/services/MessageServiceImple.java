package romaneo.unificado.services;

import java.util.Calendar;

import romaneo.unificado.daos.MessageDao;
import romaneo.unificado.domain.Message;

public class MessageServiceImple extends BaseServiceImple<Message, MessageDao>implements MessageService {

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
}
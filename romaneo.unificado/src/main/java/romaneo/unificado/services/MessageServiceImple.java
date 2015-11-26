package romaneo.unificado.services;

import java.sql.Timestamp;
import java.util.Date;

import romaneo.unificado.daos.MessageDao;
import romaneo.unificado.domain.Message;

public class MessageServiceImple extends BaseServiceImple<Message, MessageDao> implements MessageService {

	@Override
	public Integer countUnprocessedTheLastDays(Integer numberOfDays) {
		return dao.countUnprocessedTheLastDays(numberOfDays);
	}

	@Override
	public void markAsProcessed(Message message) {
		Timestamp now = new Timestamp(new Date().getTime());
		message.setProcessed(now);
		update(message);
	}
}
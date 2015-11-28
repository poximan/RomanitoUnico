package romaneo.unificado.services;

import java.util.ArrayList;
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

		List<TipoMensaje> lista = new ArrayList<>(2);
		lista.add(TipoMensaje.NORMAL);
		lista.add(TipoMensaje.URGENTE);

		return lista;
	}
}
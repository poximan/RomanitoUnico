package romaneo.unificado.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.context.ApplicationContext;

import romaneo.unificado.daos.MessageDao;
import romaneo.unificado.domain.Estado;
import romaneo.unificado.domain.Estado.EstadosPosibles;
import romaneo.unificado.domain.Message;
import romaneo.unificado.domain.Message.TipoMensaje;
import romaneo.unificado.rest.exception.LeidoException;
import romaneo.unificado.rest.exception.PendienteEnvioException;

public class MessageServiceImple extends BaseServiceImple<Message, MessageDao>implements MessageService {

	@Override
	public Integer countUnprocessedTheLastDays(Integer numberOfDays) {
		return dao.countUnprocessedTheLastDays(numberOfDays);
	}

	@Override
	public void markAsProcessed(Message message) {
		Calendar now = Calendar.getInstance();
		message.setFechaRecibidoAck(now);
		update(message);
	}

	@Override
	public List<TipoMensaje> findByTipo(String value) {

		List<TipoMensaje> lista = new ArrayList<>(2);
		lista.add(TipoMensaje.NORMAL);
		lista.add(TipoMensaje.URGENTE);

		return lista;
	}

	@Override
	public List<Message> findByParameters(Map<String, Object> parameters) {

		System.out.println("SERVICE MESSAGE");
		StringBuffer query = new StringBuffer();
		query.append("SELECT distinct e FROM " + Message.class.getSimpleName() + " e WHERE 1=1");

		Map<String, Object> queryParameters = new HashMap<String, Object>();

		if (parameters != null) {
			for (String filterKey : parameters.keySet()) {

				Object filterValue = parameters.get(filterKey);

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_DESDE.getValue())) {
					query.append(" AND e.fechaCreado >= :desde ");
					queryParameters.put("desde", filterValue);
				}

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_HASTA.getValue())) {
					query.append(" AND e.fechaHasta <= :hasta ");
					queryParameters.put("hasta", filterValue);
				}

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_DESTINATADIO.getValue())) {
					query.append(" AND e.usuario = :usuario ");
					queryParameters.put("usuario", filterValue);
				}

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_ASUNTO.getValue())) {
					query.append(" AND e.asunto LIKE :asunto ");
					queryParameters.put("asunto", "%" + filterValue + "%");
				}

				if (filterKey.equalsIgnoreCase(Message.Filters.BY_ESTADO.getValue())) {
					query.append(" AND e.estado.nombre = :estado ");
					queryParameters.put("estado", filterValue);
				}
			}
		}

		// Crear las consultas
		Query hQuery = dao.getSession().createQuery(query.toString());

		// Agrego los parametros
		for (String key : queryParameters.keySet())

			if (queryParameters.get(key) instanceof List)
				hQuery.setParameterList(key, (List) queryParameters.get(key));
			else
				hQuery.setParameter(key, queryParameters.get(key));

		@SuppressWarnings("unchecked")
		List<Message> list = hQuery.list();
		return list;
	}

	@Override
	public List<Message> findByImei(String nombreUsuario, String imei) {

		return dao.mensajesImei(nombreUsuario, imei);
	}

	@Override
	public List<Message> setEnviado(List<Message> mensajes, ApplicationContext ctx) {
		for (Message mensaje : mensajes) {

			EstadoService serv_estado = (EstadoService) ctx.getBean(EstadoService.class.getSimpleName());
			Estado nuevo_estado = serv_estado.getEstado(EstadosPosibles.ENVIADO.getValue());

			mensaje.setFechaEnviado(Calendar.getInstance());
			mensaje.setEstado(nuevo_estado);
			dao.update(mensaje);
		}
		return null;
	}

	@Override
	public boolean mensajeRecibido(Integer idMensaje, ApplicationContext ctx) throws PendienteEnvioException {
		EstadoService serv_estado = (EstadoService) ctx.getBean(EstadoService.class.getSimpleName());
		Estado nuevo_estado = serv_estado.getEstado(EstadosPosibles.RECIBIDO.getValue());
		Message mensaje;
		mensaje = dao.findById(idMensaje);
		System.out.println("estado: "+mensaje.getEstado().getNombre());
		if(mensaje.getEstado().getNombre().equals(EstadosPosibles.RECIBIDO.getValue()))
		{
			System.out.println("mensajeRecibido");
			throw new PendienteEnvioException();
		}
		mensaje.setFechaRecibidoAck(Calendar.getInstance());
		mensaje.setEstado(nuevo_estado);
		dao.update(mensaje);
		return true;
	}

	@Override
	public boolean mensajeLeido(Integer idMensaje, ApplicationContext ctx) throws LeidoException {
		EstadoService serv_estado = (EstadoService) ctx.getBean(EstadoService.class.getSimpleName());
		Estado nuevo_estado = serv_estado.getEstado(EstadosPosibles.LEIDO.getValue());
		Message mensaje;
		mensaje = dao.findById(idMensaje);
		if(mensaje.getEstado().getNombre().equals(EstadosPosibles.LEIDO.getValue()))
		{
			throw new LeidoException();
		}
		mensaje.setFechaLeidoAck(Calendar.getInstance());
		mensaje.setEstado(nuevo_estado);
		dao.update(mensaje);
		return false;
	}
}
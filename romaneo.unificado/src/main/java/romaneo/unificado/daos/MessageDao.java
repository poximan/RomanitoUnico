package romaneo.unificado.daos;

import java.util.List;

import romaneo.unificado.domain.Message;

public interface MessageDao extends BaseDao<Message, Integer> {

	Integer countUnprocessedTheLastDays(Integer numberOfDays);
	
	List<Message> mensajesImei(Integer idUsuario, String imei);

}

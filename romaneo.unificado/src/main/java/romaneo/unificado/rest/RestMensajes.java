package romaneo.unificado.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import romaneo.unificado.domain.Message;
import romaneo.unificado.domain.UsuarioMovil;
import romaneo.unificado.services.UsuarioMovilService;

@Path("mensaje")
public class RestMensajes extends BaseRest {

	@GET
	@Path("prueba")
	@Produces(MediaType.APPLICATION_JSON)
	public String fuckingPrueba() {
		return "rest Funcionando";
	}
	
	@GET
	@Path("entrante")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> mensajesRecibido(@QueryParam("idUsuario") Integer idUsuario, @QueryParam("imei") String imei) {

		List<Message> mensajes = null;

		if (autenticar(idUsuario, imei)) {

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("idUsuario", idUsuario);
			parameters.put("estado.nombre", "leido");

			mensajes = getMessageService().findQueryByParameters(null, parameters);
		}

		return mensajes;
	}
	public boolean autenticar(Integer idUsuario, String imei) {

		UsuarioMovil usuario_con_movil = ((UsuarioMovilService) getService()).findByNameIMEI(idUsuario, imei);
		return usuario_con_movil == null ? false : true;
	}

	@Override
	public String getClassName() {
		return UsuarioMovil.class.getName();
	}

	@Override
	protected String getEntityService() {
		return UsuarioMovilService.class.getSimpleName();
	}
}

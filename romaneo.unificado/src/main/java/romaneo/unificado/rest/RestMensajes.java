package romaneo.unificado.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import romaneo.unificado.domain.Message;
import romaneo.unificado.services.MessageService;
import romaneo.unificado.services.UsuarioMovilService;

@Path("mensaje")
public class RestMensajes //extends BaseRest 
{
	@Context 
	ServletContext server;
	
	
	@GET
	@Path("prueba")
	@Produces(MediaType.APPLICATION_JSON)
	public String fuckingPrueba() {
		
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(server);
		MessageService mensajeS = (MessageService) ctx.getBean(MessageService.class.getSimpleName());
		System.out.println(mensajeS);
		String respuesta ="rest Funcionando";
		
		return respuesta;
	}
	
	@GET
	@Path("entrante")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> mensajesRecibido(@QueryParam("nombreUsuario") String nombreUsuario, @QueryParam("imei") String imei) {
		
		List<Message> mensajes = null;

		if (autenticar(nombreUsuario, imei)) {
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(server);
			MessageService usuarioService = (MessageService) ctx.getBean(MessageService.class.getSimpleName());
			System.out.println("ingeso");
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("nombreUsuario", nombreUsuario);
			parameters.put("idMovil.imei", "imei");
			mensajes = usuarioService.findByParameters(parameters);
		}
		else{
			System.out.println("Error");
		}

		return mensajes;
	}
	
	public boolean autenticar(String nombreUsuario, String imei) {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(server);
		UsuarioMovilService usMoService = (UsuarioMovilService) ctx.getBean(UsuarioMovilService.class.getSimpleName());
		return usMoService.findByNameIMEI(nombreUsuario, imei) == null ? false : true;
	}
}

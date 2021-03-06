package romaneo.unificado.rest;

import java.util.List;

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
import romaneo.unificado.rest.exception.LeidoException;
import romaneo.unificado.rest.exception.NotAuthorizedException;
import romaneo.unificado.rest.exception.PendienteEnvioException;
import romaneo.unificado.services.MessageService;
import romaneo.unificado.services.UsuarioMovilService;

@Path("mensaje")
public class RestMensajes // extends BaseRest
{
	@Context
	ServletContext server;

	@GET
	@Path("prueba")
	@Produces(MediaType.APPLICATION_JSON)
	public String fuckingPrueba()
	{

		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(server);
		MessageService mensajeS = (MessageService) ctx.getBean(MessageService.class.getSimpleName());
		System.out.println(mensajeS);
		String respuesta = "rest Funcionando";
		return respuesta;
	}

	@GET
	@Path("ackLeido")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean ackMensajeLeido(@QueryParam("nombreUsuario") String nombreUsuario, @QueryParam("imei") String imei,
			@QueryParam("idMensaje") Integer idMensaje)
	{
		if (autenticar(nombreUsuario, imei))
		{
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(server);
			MessageService mensajeService = (MessageService) ctx.getBean(MessageService.class.getSimpleName());
			try
			{
				mensajeService.mensajeLeido(idMensaje, ctx);
			} catch (LeidoException e)
			{
				throw e;
			}
		} else
		{
			throw new NotAuthorizedException();
		}
		return true;
	}

	@GET
	@Path("ackRecibido")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean ackMensaje(@QueryParam("nombreUsuario") String nombreUsuario, @QueryParam("imei") String imei,
			@QueryParam("idMensaje") Integer idMensaje)
	{
		if (autenticar(nombreUsuario, imei))
		{
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(server);
			MessageService mensajeService = (MessageService) ctx.getBean(MessageService.class.getSimpleName());
			try
			{
				mensajeService.mensajeRecibido(idMensaje, ctx);
			} catch (PendienteEnvioException e)
			{
				throw e;
			}
		} else
		{
			throw new NotAuthorizedException();
		}
		return true;
	}

	@GET
	@Path("entrante")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> mensajesRecibido(@QueryParam("nombreUsuario") String nombreUsuario,
			@QueryParam("imei") String imei)
	{

		List<Message> mensajes = null;

		if (autenticar(nombreUsuario, imei))
		{
			ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(server);
			MessageService mensajeService = (MessageService) ctx.getBean(MessageService.class.getSimpleName());
			System.out.println("ingeso");
			mensajes = mensajeService.findByImei(nombreUsuario, imei);
			mensajeService.setEnviado(mensajes, ctx);
		} else
		{
			throw new NotAuthorizedException();
		}

		return mensajes;
	}

	public boolean autenticar(String nombreUsuario, String imei)
	{
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(server);
		UsuarioMovilService usMoService = (UsuarioMovilService) ctx.getBean(UsuarioMovilService.class.getSimpleName());
		return usMoService.findByNameIMEI(nombreUsuario, imei) == null ? false : true;
	}
}

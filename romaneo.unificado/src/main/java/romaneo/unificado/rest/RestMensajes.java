package romaneo.unificado.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import romaneo.unificado.domain.Message;

@Path("mensaje")
public class RestMensajes
{
	
	@GET
	@Path("prueba")
	@Produces(MediaType.APPLICATION_JSON)
	public String fuckingPrueba()
	{
		return "rest Funcionando";
	}
	
	@GET
	@Path("entrante")
	public List<Message> mensajesRecibido()
	{
		return null;
	}

}

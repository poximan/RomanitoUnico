package romaneo.unificado.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import romaneo.unificado.domain.Message;
import romaneo.unificado.domain.UsuarioMovil;

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
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> mensajesRecibido(@QueryParam("idUsuario")Integer idUsuario,@QueryParam("imei") String imei)
	{
		autenticar(idUsuario,imei);
		return null;
	}
	
	public boolean autenticar(Integer IdUsuario, String imei)
	{
		
		List<UsuarioMovil> usMoviles = new ArrayList<UsuarioMovil>();
		if(usMoviles.size()==1)
		{
			String imeiBase = usMoviles.get(0).getId_movil().getIMEI();
		}
		return true;
	}

}

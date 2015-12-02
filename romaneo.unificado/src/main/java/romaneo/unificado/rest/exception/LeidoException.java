package romaneo.unificado.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class LeidoException extends WebApplicationException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeidoException()
	{
		super(Response.status(414)
	            .entity("Este mensaje ya esta recibido").type(MediaType.TEXT_PLAIN).build());
	}

}

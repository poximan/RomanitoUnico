package romaneo.unificado.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OkException extends WebApplicationException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OkException()
	{
		 super(Response.status(Response.Status.OK)
		            .entity("ok").type(MediaType.TEXT_PLAIN).build());
	}

}

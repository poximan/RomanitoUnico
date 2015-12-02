package romaneo.unificado.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PendienteEnvioException extends WebApplicationException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PendienteEnvioException()
	{
		super(Response.status(413)
	            .entity("No ha sido enviado").type(MediaType.TEXT_PLAIN).build());
	}

}

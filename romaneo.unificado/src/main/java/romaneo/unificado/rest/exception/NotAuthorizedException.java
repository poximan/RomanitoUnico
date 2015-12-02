package romaneo.unificado.rest.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotAuthorizedException extends WebApplicationException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAuthorizedException() {
        super(Response.status(Response.Status.UNAUTHORIZED)
            .entity("No autenticado").type(MediaType.TEXT_PLAIN).build());
    }

}

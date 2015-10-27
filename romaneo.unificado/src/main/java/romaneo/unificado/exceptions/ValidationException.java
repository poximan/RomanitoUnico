package romaneo.unificado.exceptions;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = -2374016202847576474L;
	private ResourceError error;

	public ResourceError getError() {
		return error;
	}

	public ValidationException(ResourceError error) {
		this.error = error;
	}

}
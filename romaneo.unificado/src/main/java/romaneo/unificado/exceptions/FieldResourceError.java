package romaneo.unificado.exceptions;

import java.io.Serializable;

/**
 * Objeto que contiene información acerca de un error ocurrido.
 * 
 * @author Kevin Feuerschvenger
 */
public class FieldResourceError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer code;
	private String field;
	private String message;
	private String resource;

	/** Constructor. */
	public FieldResourceError() {
		this.resource = "";
		this.field = "";
		this.code = 500;
		this.message = "";
	}

	/**
	 * Constructor.
	 * 
	 * @param resource
	 *            Nombre de la entidad que originó el error.
	 * @param field
	 *            Campo que originó el error.
	 * @param code
	 *            Codigo de error.
	 * @param message
	 *            Mensaje que informa el motivo.
	 */
	public FieldResourceError(String resource, String field, Integer code, String message) {
		this.resource = resource;
		this.field = field;
		this.code = code;
		this.message = message;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FieldResourceError))
			return false;
		FieldResourceError other = (FieldResourceError) obj;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		return true;
	}

}
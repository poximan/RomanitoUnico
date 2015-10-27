package romaneo.unificado.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto de error para ser enviado en los Web Services.
 * 
 * @author Kevin Feuerschvenger
 */
public class ResourceError {

	private List<FieldResourceError> fieldErrors;
	private Integer code;
	private String message;

	public ResourceError() {
		super();
		this.fieldErrors = new ArrayList<FieldResourceError>();
		this.code = 500;
		this.message = "";
	}

	public List<FieldResourceError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldResourceError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public void addFieldError(FieldResourceError fieldError) {

		if (this.fieldErrors == null)
			this.fieldErrors = new ArrayList<FieldResourceError>();

		if (this.fieldErrors.contains(fieldError))
			return;

		this.fieldErrors.add(fieldError);
	}

	public boolean isError() {
		return this.fieldErrors == null || !this.fieldErrors.isEmpty() ? true : false;
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

}
package romaneo.unificado.services;

import org.zkoss.util.resource.Labels;

import romaneo.unificado.daos.ContactoDao;
import romaneo.unificado.domain.Contacto;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ResourceError;
import romaneo.unificado.exceptions.ValidationException;

/**
 * Servicio que integra el modelo propio de usuarios con Hibernate al de Spring
 * Security. Extiende de UserDetailsService para la conversión
 * 
 * @author Eric Hidalgo
 */
public class ContactoServiceImple extends BaseServiceImple<Contacto, ContactoDao>implements ContactoService {

	@Override
	public void validate(Contacto entity) throws ValidationException {

		ResourceError error = new ResourceError();

		if (entity.getEmail() == null || entity.getEmail().isEmpty()) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.nombre"), null,
					Labels.getLabel("acondicionador.firstNameError")));
		}
		if (entity.getTelefono() == null || entity.getTelefono().isEmpty()) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.apellido"),
					null, Labels.getLabel("acondicionador.lastNameError")));
		}
		if (entity.getDireccion() == null) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.dni"), null,
					Labels.getLabel("acondicionador.dniError")));
		}
		if (error.isError()) {
			throw new ValidationException(error);
		}
	}
}

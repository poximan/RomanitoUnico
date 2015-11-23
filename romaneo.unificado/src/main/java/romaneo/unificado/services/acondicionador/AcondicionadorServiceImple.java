package romaneo.unificado.services.acondicionador;

import java.util.List;

import org.zkoss.util.resource.Labels;

import romaneo.unificado.daos.AcondicionadorDao;
import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.BaseServiceImple;

public class AcondicionadorServiceImple extends BaseServiceImple<Acondicionador, AcondicionadorDao>
		implements AcondicionadorService {

	@Override
	public Acondicionador findByDni(Integer dni) {
		return dao.findByDni(dni);
	}

	@Override
	public List<Acondicionador> getAcondicionadoresForCombobox() {

		List<Acondicionador> acondicionadores = dao.findAll();

		// Agrego un item para obtener todas
		Acondicionador allDrv = new Acondicionador();
		allDrv.getPersona().setNombre(Labels.getLabel("allMasc"));
		allDrv.getPersona().setApellido("");
		acondicionadores.add(allDrv);

		return acondicionadores;
	}

	@Override
	public void create(Acondicionador entity) throws ValidationException {

		validate(entity);
		super.create(entity);
	}

	@Override
	public void update(Acondicionador entity) throws ValidationException {

		validate(entity);
		super.update(entity);
	}

	@Override
	public void validate(Acondicionador entity) throws ValidationException {
		ResourceError error = new ResourceError();

		if (entity.getPersona().getNombre() == null || entity.getPersona().getNombre().isEmpty()) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.nombre"), null,
					Labels.getLabel("acondicionador.firstNameError")));
		}
		if (entity.getPersona().getApellido() == null || entity.getPersona().getApellido().isEmpty()) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.apellido"),
					null, Labels.getLabel("acondicionador.lastNameError")));
		}
		if (entity.getPersona().getDni() == null) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.dni"), null,
					Labels.getLabel("acondicionador.dniError")));
		}
		if (entity.getLocalidad() == null) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.city"), null,
					Labels.getLabel("acondicionador.cityError")));
		}
		if (error.isError()) {
			throw new ValidationException(error);
		}
	}
}
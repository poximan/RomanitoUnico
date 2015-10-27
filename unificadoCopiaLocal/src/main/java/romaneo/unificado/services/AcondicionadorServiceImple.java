package romaneo.unificado.services;

import java.util.List;

import org.zkoss.util.resource.Labels;

import romaneo.unificado.daos.AcondicionadorDao;
import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ResourceError;
import romaneo.unificado.exceptions.ValidationException;

public class AcondicionadorServiceImple extends BaseServiceImple<Acondicionador, AcondicionadorDao>
		implements AcondicionadorService {

	@Override
	public Acondicionador findByDni(Integer dni) {
		return dao.findByDni(dni);
	}

	@Override
	public List<Acondicionador> getAcondicionadoresForCombobox() {
		List<Acondicionador> acondicionador = dao.findAll();

		// Agrego un item para obtener todas
		Acondicionador allDrv = new Acondicionador();
		allDrv.setFirstName(Labels.getLabel("allMasc"));
		allDrv.setLastName("");
		acondicionador.add(allDrv);

		return acondicionador;
	}

	@Override
	public void create(Acondicionador acondicionador) throws ValidationException {
		validate(acondicionador);
		super.create(acondicionador);
	}

	@Override
	public void update(Acondicionador acondicionador) throws ValidationException {
		validate(acondicionador);
		super.update(acondicionador);
	}

	@Override
	public void validate(Acondicionador entity) throws ValidationException {
		ResourceError error = new ResourceError();

		if (entity.getFirstName() == null || entity.getFirstName().isEmpty()) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.firstName"),
					null, Labels.getLabel("acondicionador.firstNameError")));
		}
		if (entity.getLastName() == null || entity.getLastName().isEmpty()) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.lastName"),
					null, Labels.getLabel("acondicionador.lastNameError")));
		}
		if (entity.getDni() == null) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.dni"), null,
					Labels.getLabel("acondicionador.dniError")));
		}
		if (entity.getCodloc() == null) {
			error.addFieldError(new FieldResourceError("acondicionador", Labels.getLabel("acondicionador.city"), null,
					Labels.getLabel("acondicionador.cityError")));
		}
		if (error.isError()) {
			throw new ValidationException(error);
		}
	}
}
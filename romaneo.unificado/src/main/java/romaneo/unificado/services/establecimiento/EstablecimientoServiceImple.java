package romaneo.unificado.services.establecimiento;

import java.util.List;

import org.zkoss.util.resource.Labels;

import romaneo.unificado.daos.EstablecimientoDao;
import romaneo.unificado.domain.Establecimiento;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.BaseServiceImple;

public class EstablecimientoServiceImple extends BaseServiceImple<Establecimiento, EstablecimientoDao>
		implements EstablecimientoService {

	@Override
	public Establecimiento findByDni(Integer dni) {
		return dao.findByDni(dni);
	}

	@Override
	public List<Establecimiento> getEstablecimientosForCombobox() {

		List<Establecimiento> establecimientos = dao.findAll();

		// Agrego un item para obtener todas
		Establecimiento allDrv = new Establecimiento();
		allDrv.setNombre_establecimiento(Labels.getLabel("allMasc"));
		establecimientos.add(allDrv);

		return establecimientos;
	}

	@Override
	public void create(Establecimiento entity) throws ValidationException {
		validate(entity);
		super.create(entity);
	}

	@Override
	public void update(Establecimiento entity) throws ValidationException {
		validate(entity);
		super.update(entity);
	}

	@Override
	public void validate(Establecimiento entity) throws ValidationException {
		ResourceError error = new ResourceError();

		if (entity.getNombre_establecimiento() == null || entity.getNombre_establecimiento().isEmpty()) {
			error.addFieldError(new FieldResourceError("establecimiento", Labels.getLabel("establecimiento.nombre"),
					null, Labels.getLabel("establecimiento.nombreError")));
		}
		if (error.isError()) {
			throw new ValidationException(error);
		}
	}
}
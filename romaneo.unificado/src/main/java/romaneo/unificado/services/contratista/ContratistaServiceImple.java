package romaneo.unificado.services.contratista;

import java.util.List;

import org.zkoss.util.resource.Labels;

import romaneo.unificado.daos.ContratistaDao;
import romaneo.unificado.domain.Contratista;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.BaseServiceImple;

public class ContratistaServiceImple extends BaseServiceImple<Contratista, ContratistaDao>
		implements ContratistaService {

	@Override
	public Contratista findByDni(Integer dni) {
		return dao.findByDni(dni);
	}

	@Override
	public List<Contratista> getContratistasForCombobox() {

		List<Contratista> contratistas = dao.findAll();

		// Agrego un item para obtener todas
		Contratista allDrv = new Contratista();
		allDrv.setNombre(Labels.getLabel("allMasc"));
		contratistas.add(allDrv);

		return contratistas;
	}

	@Override
	public void create(Contratista entity) throws ValidationException {
		validate(entity);
		super.create(entity);
	}

	@Override
	public void update(Contratista entity) throws ValidationException {
		validate(entity);
		super.update(entity);
	}

	@Override
	public void validate(Contratista entity) throws ValidationException {

		ResourceError error = new ResourceError();

		if (entity.getNombre() == null || entity.getNombre().isEmpty()) {
			error.addFieldError(new FieldResourceError("contratista", Labels.getLabel("contratista.nombre"), null,
					Labels.getLabel("contratista.nombreError")));
		}
		if (error.isError()) {
			throw new ValidationException(error);
		}
	}
}
package romaneo.unificado.services.productor;

import java.util.List;

import org.zkoss.util.resource.Labels;

import romaneo.unificado.daos.ProductorDao;
import romaneo.unificado.domain.Productor;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.BaseServiceImple;

public class ProductorServiceImple extends BaseServiceImple<Productor, ProductorDao> implements ProductorService {

	@Override
	public Productor findByDni(Integer dni) {
		return dao.findByDni(dni);
	}

	@Override
	public List<Productor> getProductoresForCombobox() {

		List<Productor> productores = dao.findAll();

		// Agrego un item para obtener todas
		Productor allDrv = new Productor();
		allDrv.setNombre_productor(Labels.getLabel("allMasc"));
		productores.add(allDrv);

		return productores;
	}

	@Override
	public void create(Productor entity) throws ValidationException {
		validate(entity);
		super.create(entity);
	}

	@Override
	public void update(Productor entity) throws ValidationException {
		validate(entity);
		super.update(entity);
	}

	@Override
	public void validate(Productor entity) throws ValidationException {

		ResourceError error = new ResourceError();

		if (entity.getNombre_productor() == null || entity.getNombre_productor().isEmpty()) {
			error.addFieldError(new FieldResourceError("productor", Labels.getLabel("productor.nombre"), null,
					Labels.getLabel("productor.nombreError")));
		}
		if (error.isError()) {
			throw new ValidationException(error);
		}
	}
}
package romaneo.unificado.controllers.establecimiento;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.controllers.BaseFormController;
import romaneo.unificado.domain.Establecimiento;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.establecimiento.EstablecimientoService;

/**
 * Formulario de establecimientos (create o update)
 * 
 * @author hugo
 */
public class EstablecimientoFormController extends BaseFormController {
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox firstNameTxtbx;

	@Wire
	private Window establecimientoFormWndw;

	private Establecimiento establecimiento;

	@Override
	public String getClassName() {
		return Establecimiento.class.getName();
	}

	@Override
	protected String getEntityService() {
		return EstablecimientoService.class.getSimpleName();
	}

	@Override
	protected Window getWindowComponent() {
		return establecimientoFormWndw;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		if ((establecimiento = (Establecimiento) Executions.getCurrent().getArg().get(SELECTED)) != null) {
			fillFields(establecimiento);
		} else {
			establecimiento = new Establecimiento();
		}
	}

	/**
	 * Completa los campos (Edicion).
	 * 
	 * @param establecimiento
	 *            Chofer cuyos datos se utilizaran para completar los datos en
	 *            el formulario.
	 */
	private void fillFields(Establecimiento establecimiento) {
		firstNameTxtbx.setValue(establecimiento.getNombre_establecimiento());
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #acceptBttn")
	@Override
	public void accept() {

		establecimiento.setNombre_establecimiento(firstNameTxtbx.getValue());

		try {
			if (establecimiento.getId() == null) {
				getService().create(establecimiento);
			} else {
				getService().update(establecimiento);
			}
			// Refrescar la lista
			Events.sendEvent("onRefresh", getWindowComponent().getParent(), null);
			getWindowComponent().onClose();
		} catch (ValidationException e) {
			e.printStackTrace();
			// Errores de validacion
			for (FieldResourceError fieldError : e.getError().getFieldErrors()) {
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("establecimiento.nombre"))) {
					firstNameTxtbx.setErrorMessage(fieldError.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Otro error
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
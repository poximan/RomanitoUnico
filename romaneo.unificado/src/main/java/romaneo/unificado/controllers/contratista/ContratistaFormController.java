package romaneo.unificado.controllers.contratista;

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
import romaneo.unificado.domain.Contratista;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.contratista.ContratistaService;

/**
 * Formulario de contratistas (create o update)
 * 
 * @author hugo
 */
public class ContratistaFormController extends BaseFormController {
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox nombreTxtbx;
	@Wire
	private Window contratistaFormWndw;

	private Contratista contratista;

	@Override
	public String getClassName() {
		return Contratista.class.getName();
	}

	@Override
	protected String getEntityService() {
		return ContratistaService.class.getSimpleName();
	}

	@Override
	protected Window getWindowComponent() {
		return contratistaFormWndw;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		if ((contratista = (Contratista) Executions.getCurrent().getArg().get(SELECTED)) != null) {
			fillFields(contratista);
		} else {
			contratista = new Contratista();
		}
	}

	/**
	 * Completa los campos (Edicion).
	 * 
	 * @param contratista
	 *            Chofer cuyos datos se utilizaran para completar los datos en
	 *            el formulario.
	 */
	private void fillFields(Contratista contratista) {
		nombreTxtbx.setValue(contratista.getNombre());
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #acceptBttn")
	@Override
	public void accept() {

		contratista.setNombre(nombreTxtbx.getValue());

		try {
			if (contratista.getId() == null) {
				getService().create(contratista);
			} else {
				getService().update(contratista);
			}
			// Refrescar la lista
			Events.sendEvent("onRefresh", getWindowComponent().getParent(), null);
			getWindowComponent().onClose();
		} catch (ValidationException e) {
			e.printStackTrace();
			// Errores de validacion
			for (FieldResourceError fieldError : e.getError().getFieldErrors()) {
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("contratista.nombre"))) {
					nombreTxtbx.setErrorMessage(fieldError.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Otro error
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
}
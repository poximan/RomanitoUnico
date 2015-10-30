package romaneo.unificado.controllers;

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
import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.domain.Productor;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.AcondicionadorService;

/**
 * Formulario de choferes (create o update)
 * 
 * @author Kevin Feuerschvenger
 */
public class ProductorFormController extends BaseFormController {
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox firstNameTxtbx;

	@Wire
	private Window productorFormWndw;

	private Productor productor;

	@Override
	public String getClassName() {
		return Acondicionador.class.getName();
	}

	@Override
	protected String getEntityService() {
		return AcondicionadorService.class.getSimpleName();
	}

	@Override
	protected Window getWindowComponent() {
		return productorFormWndw;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		if ((productor = (Productor) Executions.getCurrent().getArg().get(SELECTED)) != null)
			fillFields(productor);

		else
			productor = new Productor();

	}

	/**
	 * Completa los campos (Edicion).
	 * 
	 * @param productor
	 *            Chofer cuyos datos se utilizaran para completar los datos en
	 *            el formulario.
	 */
	private void fillFields(Productor productor) {
		firstNameTxtbx.setValue(productor.getNombre_productor());
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #acceptBttn")
	@Override
	public void accept() {
		productor.setNombre_productor(firstNameTxtbx.getValue());

		try {
			if (Long.valueOf(productor.getId()) == null) {
				getService().create(productor);
			} else {
				getService().update(productor);
			}
			// Refrescar la lista
			Events.sendEvent("onRefresh", getWindowComponent().getParent(), null);
			getWindowComponent().onClose();
		} catch (ValidationException e) {
			e.printStackTrace();
			// Errores de validacion
			for (FieldResourceError fieldError : e.getError().getFieldErrors()) {
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("productor.nombre"))) {
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
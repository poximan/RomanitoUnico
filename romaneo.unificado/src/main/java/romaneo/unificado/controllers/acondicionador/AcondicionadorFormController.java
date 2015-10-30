package romaneo.unificado.controllers.acondicionador;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.controllers.BaseFormController;
import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.domain.Localidad;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.acondicionador.AcondicionadorService;

/**
 * Formulario de acondicionadores (create o update)
 * 
 * @author hugo
 */
public class AcondicionadorFormController extends BaseFormController {
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox firstNameTxtbx, lastNameTxtbx, phonesTxtbx, emailTxtbx, addressTxtbx;
	@Wire
	private Textbox citySearchTxtbx;
	@Wire
	private Listbox citiesLstbx;
	@Wire
	private Bandbox cityBndbx;
	@Wire
	private Window acondicionadorFormWndw;
	@Wire
	private Intbox dniIntbx;

	private Acondicionador acondicionador;

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
		return acondicionadorFormWndw;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		if ((acondicionador = (Acondicionador) Executions.getCurrent().getArg().get(SELECTED)) != null) {
			cityBndbx.setValue(acondicionador.getId_localidad().getLocalidad());
			cityBndbx.setAttribute(ENTITY, acondicionador.getId_localidad().getLocalidad());
			fillFields(acondicionador);
		} else {
			acondicionador = new Acondicionador();
		}
	}

	/**
	 * Completa los campos (Edicion).
	 * 
	 * @param acondicionador
	 *            Chofer cuyos datos se utilizaran para completar los datos en
	 *            el formulario.
	 */
	private void fillFields(Acondicionador acondicionador) {
		firstNameTxtbx.setValue(acondicionador.getNombre());
		lastNameTxtbx.setValue(acondicionador.getApellido());
		phonesTxtbx.setValue(acondicionador.getTelefono());
		emailTxtbx.setValue(acondicionador.getEmail());
		addressTxtbx.setValue(acondicionador.getDireccion());
		dniIntbx.setValue(acondicionador.getDni());
	}

	@Listen("onOK = #citySearchTxtbx")
	public void findCity(Event event) {
		citiesLstbx.setVisible(true);
		citiesLstbx
				.setModel(new ListModelList<Localidad>(getLocalidadService().findByName(citySearchTxtbx.getValue())));
		citiesLstbx.setItemRenderer(new ListitemRenderer<Localidad>() {
			@Override
			public void render(Listitem item, Localidad city, int arg2) throws Exception {
				item.setLabel(city.getLocalidad());
				item.setAttribute(ENTITY, city);
			}
		});
		citiesLstbx.renderAll();
	}

	@Listen("onSelect = #citiesLstbx")
	public void selectCity() {
		if (citiesLstbx.getSelectedItem() == null) {
			return;
		}
		cityBndbx.setAttribute(ENTITY, citiesLstbx.getSelectedItem().getAttribute(ENTITY));
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #acceptBttn")
	@Override
	public void accept() {

		acondicionador.setNombre(firstNameTxtbx.getValue());
		acondicionador.setApellido(lastNameTxtbx.getValue());
		acondicionador.setDni(dniIntbx.getValue());
		acondicionador.setTelefono(phonesTxtbx.getValue());
		acondicionador.setEmail(emailTxtbx.getValue());
		acondicionador.setDireccion(addressTxtbx.getValue());
		acondicionador.setId_localidad((Localidad) cityBndbx.getAttribute(ENTITY));

		try {
			if (acondicionador.getId() == null) {
				getService().create(acondicionador);
			} else {
				getService().update(acondicionador);
			}
			// Refrescar la lista
			Events.sendEvent("onRefresh", getWindowComponent().getParent(), null);
			getWindowComponent().onClose();
		} catch (ValidationException e) {
			e.printStackTrace();
			// Errores de validacion
			for (FieldResourceError fieldError : e.getError().getFieldErrors()) {
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("acondicionador.firstName"))) {
					firstNameTxtbx.setErrorMessage(fieldError.getMessage());
				}
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("acondicionador.lastName"))) {
					lastNameTxtbx.setErrorMessage(fieldError.getMessage());
				}
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("acondicionador.dni"))) {
					dniIntbx.setErrorMessage(fieldError.getMessage());
				}
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("acondicionador.city"))) {
					cityBndbx.setErrorMessage(fieldError.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Otro error
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
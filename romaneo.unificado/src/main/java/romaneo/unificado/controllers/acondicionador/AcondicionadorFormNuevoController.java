package romaneo.unificado.controllers.acondicionador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.controllers.BaseTabFormList;
import romaneo.unificado.controllers.FormPageName;
import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.domain.Contacto;
import romaneo.unificado.domain.Localidad;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.acondicionador.AcondicionadorService;

/**
 * Formulario de acondicionadores (create o update)
 * 
 * @author hugo
 */
public class AcondicionadorFormNuevoController extends BaseTabFormList<Acondicionador>implements FormPageName {

	private static final long serialVersionUID = 1L;

	protected List<Contacto> all = new ArrayList<Contacto>();

	@Wire
	private Combobox contactosPageSizeCmbbx;
	@Wire
	private Paging contactosPgn;
	@Wire
	private Textbox nombreTxtbx, apellidoTxtbx;
	@Wire
	private Textbox localidadSearchTxtbx;
	@Wire
	private Listbox localidadesLstbx, contactosLstbx;
	@Wire
	private Bandbox localidadBndbx;
	@Wire
	private Window acondicionadorFormNuevoWndw;
	@Wire
	private Intbox dniIntbx;

	private Acondicionador acondicionador;

	protected boolean executeAndRenderPagedQueryInitial = true;

	@Override
	public String getClassName() {
		return Acondicionador.class.getName();
	}

	@Override
	protected String getEntityService() {
		return AcondicionadorService.class.getSimpleName();
	}

	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);

		String string_id;

		if ((string_id = Executions.getCurrent().getParameter("id")) != null) {

			acondicionador = (Acondicionador) getService().find(Integer.valueOf(string_id));
			localidadBndbx.setValue(acondicionador.getLocalidad().getNombre_localidad());
			localidadBndbx.setAttribute(ENTITY, acondicionador.getLocalidad());
			fillFields(acondicionador);
		}

		Map<String, Serializable> arg = new HashMap<String, Serializable>();
		arg.put(SELECTED, (Serializable) acondicionador);

		setArgs(arg);
		doCompose(comp);
	}

	/**
	 * Completa los campos (Edicion).
	 * 
	 * @param acondicionador
	 *            Chofer cuyos datos se utilizaran para completar los datos en
	 *            el formulario.
	 */
	private void fillFields(Acondicionador acondicionador) {
		nombreTxtbx.setValue(acondicionador.getPersona().getNombre());
		apellidoTxtbx.setValue(acondicionador.getPersona().getApellido());
		dniIntbx.setValue(acondicionador.getPersona().getDni());
	}

	@Listen("onOK = #localidadSearchTxtbx")
	public void findCity(Event event) {

		localidadesLstbx.setVisible(true);
		localidadesLstbx.setModel(
				new ListModelList<Localidad>(getLocalidadService().findByName(localidadSearchTxtbx.getValue())));
		localidadesLstbx.setItemRenderer(new ListitemRenderer<Localidad>() {
			@Override
			public void render(Listitem item, Localidad city, int arg2) throws Exception {
				item.setLabel(city.getNombre_localidad());
				item.setAttribute(ENTITY, city);
			}
		});
		localidadesLstbx.renderAll();
	}

	@Listen("onSelect = #localidadesLstbx")
	public void selectCity() {
		if (localidadesLstbx.getSelectedItem() == null) {
			return;
		}
		localidadBndbx.setAttribute(ENTITY, localidadesLstbx.getSelectedItem().getAttribute(ENTITY));
	}

	@Listen("onClick = #editarBttn")
	public void edit() {

		if (nombreTxtbx.isReadonly()) {
			nombreTxtbx.setReadonly(false);
			apellidoTxtbx.setReadonly(false);
			localidadBndbx.setReadonly(false);
			dniIntbx.setReadonly(false);
		} else {
			nombreTxtbx.setReadonly(true);
			apellidoTxtbx.setReadonly(true);
			localidadBndbx.setReadonly(true);
			dniIntbx.setReadonly(true);
		}
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #volverBttn")
	@Override
	public void accept() {

		acondicionador.getPersona().setNombre(nombreTxtbx.getValue());
		acondicionador.getPersona().setApellido(apellidoTxtbx.getValue());
		acondicionador.getPersona().setDni(dniIntbx.getValue());
		acondicionador.setLocalidad((Localidad) localidadBndbx.getAttribute(ENTITY));

		try {
			if (acondicionador.getId() == null) {
				getService().create(acondicionador);
			} else {
				getService().update(acondicionador);
			}
			// Refrescar la lista
			// Events.sendEvent("onRefresh", getWindowComponent().getParent(),
			// null);
			getWindowComponent().onClose();

		} catch (ValidationException e) {
			e.printStackTrace();
			// Errores de validacion
			for (FieldResourceError fieldError : e.getError().getFieldErrors()) {
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("persona.nombre"))) {
					nombreTxtbx.setErrorMessage(fieldError.getMessage());
				}
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("persona.apellido"))) {
					apellidoTxtbx.setErrorMessage(fieldError.getMessage());
				}
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("persona.dni"))) {
					dniIntbx.setErrorMessage(fieldError.getMessage());
				}
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("persona.localidad"))) {
					localidadBndbx.setErrorMessage(fieldError.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Otro error
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

	/* ............................................. */
	/* ............................................. */
	/* LISTA ....................................... */
	/* ............................................. */

	@Override
	protected ListitemRenderer<Contacto> getListitemRender() {

		return new ListitemRenderer<Contacto>() {
			@Override
			public void render(Listitem item, Contacto contacto, int arg) throws Exception {

				(new Listcell("" + contacto.getEmail() != null ? contacto.getEmail() : "")).setParent(item);

				(new Listcell("" + contacto.getTelefono() != null ? contacto.getTelefono() : "")).setParent(item);

				(new Listcell("" + contacto.getDireccion() != null ? contacto.getDireccion() : "")).setParent(item);

				item.setAttribute(ENTITY, contacto);
			}
		};
	}

	public Acondicionador getAcondicionador() {
		return acondicionador;
	}

	@Override
	protected Listbox getListComponent() {
		return contactosLstbx;
	}

	@Override
	protected Window getWindowComponent() {
		return acondicionadorFormNuevoWndw;
	}

	@Override
	public void buildParameters() {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (acondicionador.getPersona() != null) {
			parameters.put(Contacto.Filters.BY_PERSONA.getValue(), acondicionador.getPersona());
		}

		executeAndRenderPagedQuery(parameters);
	}

	@Override
	protected Paging getPagingComponent() {
		return contactosPgn;
	}

	@Override
	protected Combobox getPageSizeComponent() {
		return contactosPageSizeCmbbx;
	}

	@Listen("onSelect = #acondicionadoresLstbx")
	public void onSelect$acondicionadoresLstbx(Event event) {
		super.enableButtons();
	}

	@Override
	public String getFormPageName() {
		return "acondicionadorFormNuevo.zul";
	}
}
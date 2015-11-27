package romaneo.unificado.controllers.mensajes;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.controllers.BaseFormController;
import romaneo.unificado.domain.Message;
import romaneo.unificado.domain.Message.TipoMensaje;
import romaneo.unificado.domain.Persona;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.MessageService;

/**
 * Formulario de mensajees (create o update)
 * 
 * @author hugo
 */
public class MensajeSalidaFormController extends BaseFormController {

	private static final long serialVersionUID = 1L;

	@Wire
	private Window mensajeSalidaFormWndw;

	/*
	 * celdas simples
	 */
	@Wire
	private Textbox asuntoTxtbx, contenidoTxtbx;

	/*
	 * celda desplegable - tipo de mensaje
	 */
	@Wire
	private Bandbox tipoBndbx;
	@Wire
	private Textbox tipoSearchTxtbx;
	@Wire
	private Listbox tipoLstbx;

	/*
	 * celda desplegable - destinatario del mensaje
	 */
	@Wire
	private Bandbox destinatarioBndbx;
	@Wire
	private Textbox destinatarioSearchTxtbx;
	@Wire
	private Listbox destinatarioLstbx;

	private Message mensaje;

	@Override
	public String getClassName() {
		return Message.class.getName();
	}

	@Override
	protected String getEntityService() {
		return MessageService.class.getSimpleName();
	}

	@Override
	protected Window getWindowComponent() {
		return mensajeSalidaFormWndw;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		if ((mensaje = (Message) Executions.getCurrent().getArg().get(SELECTED)) != null) {

			tipoBndbx.setValue(mensaje.getTipo_mensaje().getValue());
			tipoBndbx.setAttribute(ENTITY, mensaje.getTipo_mensaje().getValue());

			destinatarioBndbx.setValue(mensaje.getUsuario().getPersona().getClass().getSimpleName());
			destinatarioBndbx.setAttribute(ENTITY, mensaje.getUsuario().getPersona());

			fillFields(mensaje);
		} else {
			mensaje = new Message();
		}
	}

	/**
	 * Completa los campos (Edicion).
	 * 
	 * @param mensaje
	 *            Chofer cuyos datos se utilizaran para completar los datos en
	 *            el formulario.
	 */
	private void fillFields(Message mensaje) {

		asuntoTxtbx.setValue(mensaje.getAsunto());
		contenidoTxtbx.setValue(mensaje.getMensaje());
	}

	@Listen("onOK = #tipoSearchTxtbx")
	public void findTipo(Event event) {

		tipoLstbx.setVisible(true);
		tipoLstbx.setModel(new ListModelList<TipoMensaje>(getMessageService().findByTipo(tipoSearchTxtbx.getValue())));
		tipoLstbx.setItemRenderer(new ListitemRenderer<TipoMensaje>() {
			@Override
			public void render(Listitem item, TipoMensaje tipo, int arg2) throws Exception {
				item.setLabel(tipo.getValue());
				item.setAttribute(ENTITY, tipo);
			}
		});
		tipoLstbx.renderAll();
	}

	@Listen("onSelect = #citiesLstbx")
	public void selectCity() {
		if (tipoLstbx.getSelectedItem() == null) {
			return;
		}
		tipoLstbx.setAttribute(ENTITY, tipoLstbx.getSelectedItem().getAttribute(ENTITY));
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #acceptBttn")
	@Override
	public void accept() {

		mensaje.setAsunto(asuntoTxtbx.getValue());
		mensaje.setMensaje(contenidoTxtbx.getValue());
		mensaje.getUsuario().setPersona((Persona) destinatarioBndbx.getAttribute(destinatarioBndbx.getValue()));

		try {
			if (mensaje.getId() == null) {
				getService().create(mensaje);
			} else {
				getService().update(mensaje);
			}
			// Refrescar la lista
			Events.sendEvent("onRefresh", getWindowComponent().getParent(), null);
			getWindowComponent().onClose();

		} catch (ValidationException e) {
			e.printStackTrace();
			// Errores de validacion
			for (FieldResourceError fieldError : e.getError().getFieldErrors()) {
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("mensaje.asunto"))) {
					asuntoTxtbx.setErrorMessage(fieldError.getMessage());
				}
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("mensaje.contenido"))) {
					contenidoTxtbx.setErrorMessage(fieldError.getMessage());
				}
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("mensaje.tipo"))) {
					destinatarioBndbx.setErrorMessage(fieldError.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// Otro error
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}

}
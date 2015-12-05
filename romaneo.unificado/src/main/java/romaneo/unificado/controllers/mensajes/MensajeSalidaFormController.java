package romaneo.unificado.controllers.mensajes;

import java.util.Calendar;

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
import romaneo.unificado.domain.Estado.EstadosPosibles;
import romaneo.unificado.domain.Message;
import romaneo.unificado.domain.Message.TipoMensaje;
import romaneo.unificado.domain.Usuario;
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

			tipoBndbx.setValue(mensaje.getTipoMensaje().getValue());
			tipoBndbx.setAttribute(ENTITY, mensaje.getTipoMensaje().getValue());

			destinatarioBndbx.setValue(mensaje.getUsuario().getPersona().getNombre());
			destinatarioBndbx.setAttribute(ENTITY, mensaje.getUsuario());

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

	@Listen("onClick = #editBttn")
	public void edit() {

		if (mensaje.getEstado().getNombre().equals(EstadosPosibles.GENERADO.getValue()))
			if (destinatarioBndbx.isReadonly()) {
				destinatarioBndbx.setReadonly(false);
				contenidoTxtbx.setReadonly(false);
				asuntoTxtbx.setReadonly(false);
				tipoBndbx.setReadonly(false);
			} else {
				destinatarioBndbx.setReadonly(true);
				contenidoTxtbx.setReadonly(true);
				asuntoTxtbx.setReadonly(true);
				tipoBndbx.setReadonly(true);
			}
		else {
			alert("El mensaje ya fue enviado, no puede modificarse");
		}
	}

	@Listen("onSelect = #tipoLstbx")
	public void selectTipo() {
		if (tipoLstbx.getSelectedItem() == null) {
			return;
		}
		tipoBndbx.setAttribute(ENTITY, tipoLstbx.getSelectedItem().getAttribute(ENTITY));
	}

	@Listen("onOK = #destinatarioSearchTxtbx")
	public void findDestinatarioTipo(Event event) {

		destinatarioLstbx.setVisible(true);
		destinatarioLstbx.setModel(
				new ListModelList<Usuario>(getUsuarioService().findByLikeName(destinatarioSearchTxtbx.getValue())));
		destinatarioLstbx.setItemRenderer(new ListitemRenderer<Usuario>() {

			@Override
			public void render(Listitem item, Usuario tipo, int arg2) throws Exception {
				item.setLabel(tipo.getPersona().getNombre());
				item.setAttribute(ENTITY, tipo);
			}
		});
		destinatarioLstbx.renderAll();
	}

	@Listen("onSelect = #destinatarioLstbx")
	public void selectDestinatario() {
		if (destinatarioLstbx.getSelectedItem() == null) {
			return;
		}
		destinatarioBndbx.setAttribute(ENTITY, destinatarioLstbx.getSelectedItem().getAttribute(ENTITY));
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #acceptBttn")
	@Override
	public void accept() {

		mensaje.setFechaCreado(Calendar.getInstance());
		mensaje.setAsunto(asuntoTxtbx.getValue());
		mensaje.setMensaje(contenidoTxtbx.getValue());
		mensaje.setUsuario((Usuario) destinatarioBndbx.getAttribute(ENTITY));
		mensaje.setTipoMensaje((TipoMensaje) tipoBndbx.getAttribute(ENTITY));
		mensaje.setEstado(getEstadoService().getEstado(EstadosPosibles.GENERADO.getValue()));

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
					tipoBndbx.setErrorMessage(fieldError.getMessage());
				}
				if (fieldError.getField().equalsIgnoreCase(Labels.getLabel("mensaje.destinatario"))) {
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
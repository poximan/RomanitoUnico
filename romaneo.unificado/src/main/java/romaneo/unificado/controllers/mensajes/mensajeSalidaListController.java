/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.controllers.mensajes;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.controllers.BasePagedListController;
import romaneo.unificado.domain.Message;
import romaneo.unificado.services.MessageService;

/* ............................................. */
/* ............................................. */
/* CLASE ....................................... */
/* ............................................. */

public class mensajeSalidaListController extends BasePagedListController<Message> {

	/* ............................................. */
	/* ............................................. */
	/* ATRIBUTOS ................................... */
	/* ............................................. */

	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox mensajesSalidaPageSizeCmbbx;
	@Wire
	private Window mensajeSalidaListWndw;
	@Wire
	private Listbox mensajesSalidaLstbx;
	@Wire
	private Paging mensajesSalidaPgn;

	// **** FILTROS **** //
	@Wire
	private Datebox filterByFechaDesde;
	@Wire
	private Datebox filterByFechaHasta;
	@Wire
	private Textbox filterByDestinatario;
	@Wire
	private Textbox filterByAsunto;
	@Wire
	private Textbox filterByEstado;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public mensajeSalidaListController() {

	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	protected Listbox getListComponent() {
		return mensajesSalidaLstbx;
	}

	@Override
	protected Paging getPagingComponent() {
		return mensajesSalidaPgn;
	}

	@Override
	protected Combobox getPageSizeComponent() {
		return mensajesSalidaPageSizeCmbbx;
	}

	@Override
	protected ListitemRenderer<Message> getListitemRender() {

		return new ListitemRenderer<Message>() {
			@Override
			public void render(Listitem item, Message mensaje, int arg) throws Exception {

				(new Listcell("" + (mensaje.getFecha_creado() != null ? mensaje.getFecha_creado() : "")))
						.setParent(item);

				(new Listcell("" + (mensaje.getUsuario() != null ? mensaje.getUsuario().getPersona() : "")))
						.setParent(item);

				(new Listcell("" + (mensaje.getAsunto() != null ? mensaje.getAsunto() : ""))).setParent(item);

				(new Listcell("" + (mensaje.getEstado() != null ? mensaje.getEstado() : ""))).setParent(item);

				item.setAttribute(ENTITY, mensaje);
			}
		};
	}

	@Override
	protected String getFormPageName() {
		return Labels.getLabel("url.mensajeSalidaForm");
	}

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
		return mensajeSalidaListWndw;
	}

	/* ------------------------------------------------------------------ */
	/* filtros ---------------------------------------------------------- */
	/* ------------------------------------------------------------------ */

	@Override
	public void buildParameters() {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (filterByFechaDesde.getValue() != null && !filterByFechaDesde.getValue().toString().trim().isEmpty()) {
			parameters.put(Message.Filters.BY_DESDE.getValue(), filterByFechaDesde.getValue());
		}
		if (filterByFechaHasta.getValue() != null && !filterByFechaHasta.getValue().toString().trim().isEmpty()) {
			parameters.put(Message.Filters.BY_HASTA.getValue(), filterByFechaHasta.getValue());
		}
		if (filterByDestinatario.getValue() != null && !filterByDestinatario.getValue().trim().isEmpty()) {
			parameters.put(Message.Filters.BY_DESTINATADIO.getValue(), filterByDestinatario.getValue());
		}
		if (filterByAsunto.getValue() != null && !filterByAsunto.getValue().trim().isEmpty()) {
			parameters.put(Message.Filters.BY_ASUNTO.getValue(), filterByAsunto.getValue());
		}
		if (filterByEstado.getValue() != null && !filterByEstado.getValue().trim().isEmpty()) {
			parameters.put(Message.Filters.BY_ESTADO.getValue(), filterByEstado.getValue());
		}

		executeAndRenderPagedQuery(parameters);
	}

	@Listen("onSelect = #mensajesSalidaLstbx")
	public void onSelect$mensajesSalidaLstbx(Event event) {
		super.enableButtons();
	}

	@Listen("onClick = #cleanFiltersBttn")
	public void cleanFilters() {

		filterByFechaDesde.setValue(null);
		filterByFechaHasta.setValue(null);
		filterByDestinatario.setValue(null);
		filterByAsunto.setValue(null);
		filterByEstado.setValue(null);
	}

	/* ------------------------------------------------------------------ */
	/* onClick - navegacion contenedor ---------------------------------- */
	/* ------------------------------------------------------------------ */

	@Listen("onClick = #mensajeEntradaSubmenuA2")
	public void navigationToMensajesEntrada() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToMensajesEntrada", mainWndw, null);
		}
	}

	@Listen("onClick = #mensajeSalidaSubmenuA2")
	public void navigationToMensajesSalida() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToMensajesSalida", mainWndw, null);
		}
	}
}

/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.controllers.mensajes;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.DesktopUnavailableException;
import org.zkoss.zk.ui.Executions;
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

public class MensajeSalidaListController extends BasePagedListController<Message> {

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

	private Timer timer;

	/* ............................................. */
	/* ............................................. */
	/* CONSTRUCTOR ................................. */
	/* ............................................. */

	public MensajeSalidaListController() {

	}

	/* ............................................. */
	/* ............................................. */
	/* METODOS ..................................... */
	/* ............................................. */

	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);

		// http://www.zkoss.org/zkdemo/zk_pe_and_ee/server_push_comet

		if (timer != null)
			timer.cancel();
		timer = new Timer();
		timer.schedule(createUpdateTask(), 0, 10000);
	}

	private TimerTask createUpdateTask() {
		return new TimerTask() {
			@Override
			public void run() {
				updateInfo();
			}
		};
	}

	private void updateInfo() {
		try {
			Desktop desktop = mensajesSalidaLstbx.getDesktop();
			if (desktop == null) {
				timer.cancel();
				return;
			}

			Executions.activate(desktop);
			try {

				List<Listitem> mensajes_viejos = getListComponent().getItems();

				Listbox mensajes_nuevos = new Listbox();
				mensajes_nuevos.setItemRenderer(getListitemRender());

				if (!mensajes_viejos.equals(mensajes_nuevos))
					getListComponent().setItemRenderer(getListitemRender());

			} finally {
				Executions.deactivate(desktop);
			}
		} catch (DesktopUnavailableException ex) {
			System.out.println("Desktop currently unavailable");
			timer.cancel();
		} catch (InterruptedException e) {
			System.out.println("The server push thread interrupted");
			timer.cancel();
		}
	}

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

				try {
					new Listcell(
							"" + (mensaje.getFechaCreado().getTime() != null ? mensaje.getFechaCreado().getTime() : "")//
					).setParent(item);

				} catch (NullPointerException excepcion) {
					new Listcell("").setParent(item);
				}

				(new Listcell("" + (mensaje.getUsuario() != null ? mensaje.getUsuario() : ""))).setParent(item);

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

			Calendar desde = Calendar.getInstance();
			desde.setTime(filterByFechaDesde.getValue());
			parameters.put(Message.Filters.BY_DESDE.getValue(), desde);
		}
		if (filterByFechaHasta.getValue() != null && !filterByFechaHasta.getValue().toString().trim().isEmpty()) {

			Calendar hasta = Calendar.getInstance();
			hasta.setTime(filterByFechaHasta.getValue());
			parameters.put(Message.Filters.BY_HASTA.getValue(), hasta);
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

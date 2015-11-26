/* ............................................. */
/* ............................................. */
/* PRELIMINAR .................................. */
/* ............................................. */

package romaneo.unificado.controllers.mensajes;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
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
			public void render(Listitem item, Message Message, int arg) throws Exception {

				(new Listcell("" + (Message.getId() != null ? Message.getId() : ""))).setParent(item);

				(new Listcell("" + Message.getData() != null ? Message.getData() : "")).setParent(item);

				(new Listcell("" + (Message.getCreated() != null ? Message.getCreated() : ""))).setParent(item);

				item.setAttribute(ENTITY, Message);
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

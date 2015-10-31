package romaneo.unificado.controllers.productor;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.controllers.BasePagedListController;
import romaneo.unificado.domain.Productor;
import romaneo.unificado.services.productor.ProductorService;

/**
 * Lista de productores
 * 
 * @author hugo
 */
public class ProductorListController extends BasePagedListController<Productor> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox productoresPageSizeCmbbx;
	@Wire
	private Window productoresListWndw;
	@Wire
	private Listbox productoresLstbx;
	@Wire
	private Paging productoresPgn;

	// **** FILTROS **** //
	@Wire
	private Textbox filterByNombre;

	@Override
	protected ListitemRenderer<Productor> getListitemRender() {

		return new ListitemRenderer<Productor>() {
			@Override
			public void render(Listitem item, Productor productor, int arg) throws Exception {

				(new Listcell("" + productor.getNombre_productor() != null ? productor.getNombre_productor() : ""))
						.setParent(item);

				item.setAttribute(ENTITY, productor);
			}
		};
	}

	@Override
	protected String getFormPageName() {
		return Labels.getLabel("url.productoresForm");
	}

	@Override
	public String getClassName() {
		return Productor.class.getName();
	}

	@Override
	protected String getEntityService() {
		return ProductorService.class.getSimpleName();
	}

	@Override
	protected Listbox getListComponent() {
		return productoresLstbx;
	}

	@Override
	protected Window getWindowComponent() {
		return productoresListWndw;
	}

	@Override
	protected Paging getPagingComponent() {
		return productoresPgn;
	}

	@Override
	protected Combobox getPageSizeComponent() {
		return productoresPageSizeCmbbx;
	}

	@Override
	public void buildParameters() {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (filterByNombre.getValue() != null && !filterByNombre.getValue().trim().isEmpty()) {
			parameters.put(Productor.Filters.BY_NOMBRE.getValue(), filterByNombre.getValue());
		}

		executeAndRenderPagedQuery(parameters);
	}

	@Listen("onSelect = #productoresLstbx")
	public void onSelect$productoresLstbx(Event event) {
		super.enableButtons();
	}

	@Listen("onClick = #cleanFiltersBttn")
	public void cleanFilters() {
		filterByNombre.setValue(null);
	}

	/* ------------------------------------------------------------------ */
	/* onClick - navegacion contenedor ---------------------------------- */
	/* ------------------------------------------------------------------ */

	@Listen("onClick = #acondicionadoresSubmenuA2")
	public void navigationToAcondicionadores() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToAcondicionadores", mainWndw, null);
		}
	}

	@Listen("onClick = #productoresSubmenuA2")
	public void navigationToProductores() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToProductores", mainWndw, null);
		}
	}

	@Listen("onClick = #establecimientosSubmenuA2")
	public void navigationToEstablecimiento() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToEstablecimientos", mainWndw, null);
		}
	}

	@Listen("onClick = #contratistasSubmenuA2")
	public void navigationToContratistas() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToContratistas", mainWndw, null);
		}
	}
}

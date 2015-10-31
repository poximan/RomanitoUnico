package romaneo.unificado.controllers.establecimiento;

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
import romaneo.unificado.domain.Establecimiento;
import romaneo.unificado.services.establecimiento.EstablecimientoService;

/**
 * Lista de establecimientos
 * 
 * @author hugo
 */
public class EstablecimientoListController extends BasePagedListController<Establecimiento> {
	
	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox establecimientosPageSizeCmbbx;
	@Wire
	private Window establecimientosListWndw;
	@Wire
	private Listbox establecimientosLstbx;
	@Wire
	private Paging establecimientosPgn;

	// **** FILTROS **** //
	@Wire
	private Textbox filterByNombre;

	@Override
	protected ListitemRenderer<Establecimiento> getListitemRender() {

		return new ListitemRenderer<Establecimiento>() {
			@Override
			public void render(Listitem item, Establecimiento establecimiento, int arg) throws Exception {
				(new Listcell("" + establecimiento.getNombre_establecimiento() != null
						? establecimiento.getNombre_establecimiento() : "")).setParent(item);

				item.setAttribute(ENTITY, establecimiento);
			}
		};
	}

	@Override
	protected String getFormPageName() {
		return Labels.getLabel("url.establecimientoForm");
	}

	@Override
	public String getClassName() {
		return Establecimiento.class.getName();
	}

	@Override
	protected String getEntityService() {
		return EstablecimientoService.class.getSimpleName();
	}

	@Override
	protected Listbox getListComponent() {
		return establecimientosLstbx;
	}

	@Override
	protected Window getWindowComponent() {
		return establecimientosListWndw;
	}

	@Override
	protected Paging getPagingComponent() {
		return establecimientosPgn;
	}

	@Override
	protected Combobox getPageSizeComponent() {
		return establecimientosPageSizeCmbbx;
	}

	@Override
	public void buildParameters() {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (filterByNombre.getValue() != null && !filterByNombre.getValue().trim().isEmpty()) {
			parameters.put(Establecimiento.Filters.BY_FIRST_NAME.getValue(), filterByNombre.getValue());
		}

		executeAndRenderPagedQuery(parameters);
	}

	@Listen("onSelect = #establecimientosLstbx")
	public void onSelect$establecimientosLstbx(Event event) {
		super.enableButtons();
	}

	@Listen("onClick = #cleanFiltersBttn")
	public void cleanFilters() {
		filterByNombre.setValue(null);
	}

	/* ------------------------------------------------------------------ */
	/* onClick - navegacion contenedor ---------------------------------- */
	/* ------------------------------------------------------------------ */

	@Listen("onClick = #acondicionadoresSubmenuA3")
	public void navigationToAcondicionadores() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToAcondicionadores", mainWndw, null);
		}
	}

	@Listen("onClick = #productoresSubmenuA3")
	public void navigationToProductores() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToProductores", mainWndw, null);
		}
	}

	@Listen("onClick = #establecimientosSubmenuA3")
	public void navigationToEstablecimiento() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToEstablecimientos", mainWndw, null);
		}
	}

	@Listen("onClick = #contratistasSubmenuA3")
	public void navigationToContratistas() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToContratistas", mainWndw, null);
		}
	}
}

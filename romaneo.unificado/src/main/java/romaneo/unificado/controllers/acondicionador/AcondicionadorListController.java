package romaneo.unificado.controllers.acondicionador;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.controllers.BasePagedListController;
import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.services.acondicionador.AcondicionadorService;

/**
 * Lista de acondicionadores
 * 
 * @author hugo
 */
public class AcondicionadorListController extends BasePagedListController<Acondicionador> {
	
	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox acondicionadoresPageSizeCmbbx;
	@Wire
	private Window acondicionadoresListWndw;
	@Wire
	private Listbox acondicionadoresLstbx;
	@Wire
	private Paging acondicionadoresPgn;

	// **** FILTROS **** //
	@Wire
	private Textbox filterByApellidoTxtbx;
	@Wire
	private Textbox filterByNombreTxtbx;
	@Wire
	private Intbox filterByDni;

	@Override
	protected ListitemRenderer<Acondicionador> getListitemRender() {

		return new ListitemRenderer<Acondicionador>() {
			@Override
			public void render(Listitem item, Acondicionador acondicionador, int arg) throws Exception {
				(new Listcell("" + acondicionador.getApellido() != null ? acondicionador.getApellido() : ""))
						.setParent(item);
				(new Listcell("" + acondicionador.getNombre() != null ? acondicionador.getNombre() : ""))
						.setParent(item);
				(new Listcell("" + acondicionador.getTelefono() != null ? acondicionador.getTelefono() : ""))
						.setParent(item);
				(new Listcell("" + acondicionador.getEmail() != null ? acondicionador.getEmail() : "")).setParent(item);
				item.setAttribute(ENTITY, acondicionador);
			}
		};
	}

	@Override
	protected String getFormPageName() {
		return Labels.getLabel("url.acondicionadorForm");
	}

	@Override
	public String getClassName() {
		return Acondicionador.class.getName();
	}

	@Override
	protected String getEntityService() {
		return AcondicionadorService.class.getSimpleName();
	}

	@Override
	protected Listbox getListComponent() {
		return acondicionadoresLstbx;
	}

	@Override
	protected Window getWindowComponent() {
		return acondicionadoresListWndw;
	}

	@Override
	protected Paging getPagingComponent() {
		return acondicionadoresPgn;
	}

	@Override
	protected Combobox getPageSizeComponent() {
		return acondicionadoresPageSizeCmbbx;
	}

	@Override
	public void buildParameters() {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (filterByNombreTxtbx.getValue() != null && !filterByNombreTxtbx.getValue().trim().isEmpty()) {
			parameters.put(Acondicionador.Filters.BY_NOMBRE.getValue(), filterByNombreTxtbx.getValue());
		}
		if (filterByApellidoTxtbx.getValue() != null && !filterByApellidoTxtbx.getValue().trim().isEmpty()) {
			parameters.put(Acondicionador.Filters.BY_APELLIDO.getValue(), filterByApellidoTxtbx.getValue());
		}
		if (filterByDni.getValue() != null) {
			parameters.put(Acondicionador.Filters.BY_DNI.getValue(), filterByDni.getValue());
		}

		executeAndRenderPagedQuery(parameters);
	}

	@Listen("onSelect = #acondicionadoresLstbx")
	public void onSelect$acondicionadoresLstbx(Event event) {
		super.enableButtons();
	}

	@Listen("onClick = #cleanFiltersBttn")
	public void cleanFilters() {
		filterByNombreTxtbx.setValue(null);
		filterByApellidoTxtbx.setValue(null);
		filterByDni.setValue(null);
	}

	/* ------------------------------------------------------------------ */
	/* onClick - navegacion contenedor ---------------------------------- */
	/* ------------------------------------------------------------------ */

	@Listen("onClick = #acondicionadoresSubmenuA1")
	public void navigationToAcondicionadores() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToAcondicionadores", mainWndw, null);
		}
	}

	@Listen("onClick = #productoresSubmenuA1")
	public void navigationToProductores() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToProductores", mainWndw, null);
		}
	}

	@Listen("onClick = #establecimientosSubmenuA1")
	public void navigationToEstablecimiento() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToEstablecimientos", mainWndw, null);
		}
	}

	@Listen("onClick = #contratistasSubmenuA1")
	public void navigationToContratistas() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToContratistas", mainWndw, null);
		}
	}
}

package romaneo.unificado.controllers.contratista;

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
import romaneo.unificado.domain.Contratista;
import romaneo.unificado.services.contratista.ContratistaService;

/**
 * Lista de contratistas
 * 
 * @author hugo
 */
public class ContratistaListController extends BasePagedListController<Contratista> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Combobox contratistasPageSizeCmbbx;
	@Wire
	private Window contratistasListWndw;
	@Wire
	private Listbox contratistasLstbx;
	@Wire
	private Paging contratistasPgn;

	// **** FILTROS **** //
	@Wire
	private Textbox filterByLastNameTxtbx;
	@Wire
	private Textbox filterByFirstNameTxtbx;
	@Wire
	private Intbox filterByDniIntbx;

	@Override
	protected ListitemRenderer<Contratista> getListitemRender() {

		return new ListitemRenderer<Contratista>() {
			@Override
			public void render(Listitem item, Contratista contratista, int arg) throws Exception {

				(new Listcell("" + contratista.getNombre() != null ? contratista.getNombre() : "")).setParent(item);

				item.setAttribute(ENTITY, contratista);
			}
		};
	}

	@Override
	protected String getFormPageName() {
		return Labels.getLabel("url.contratistaForm");
	}

	@Override
	public String getClassName() {
		return Contratista.class.getName();
	}

	@Override
	protected String getEntityService() {
		return ContratistaService.class.getSimpleName();
	}

	@Override
	protected Listbox getListComponent() {
		return contratistasLstbx;
	}

	@Override
	protected Window getWindowComponent() {
		return contratistasListWndw;
	}

	@Override
	protected Paging getPagingComponent() {
		return contratistasPgn;
	}

	@Override
	protected Combobox getPageSizeComponent() {
		return contratistasPageSizeCmbbx;
	}

	@Override
	public void buildParameters() {

		Map<String, Object> parameters = new HashMap<String, Object>();

		if (filterByFirstNameTxtbx.getValue() != null && !filterByFirstNameTxtbx.getValue().trim().isEmpty()) {
			parameters.put(Contratista.Filters.BY_FIRST_NAME.getValue(), filterByFirstNameTxtbx.getValue());
		}

		executeAndRenderPagedQuery(parameters);
	}

	@Listen("onSelect = #contratistasLstbx")
	public void onSelect$contratistasLstbx(Event event) {
		super.enableButtons();
	}

	@Listen("onClick = #cleanFiltersBttn")
	public void cleanFilters() {
		filterByFirstNameTxtbx.setValue(null);
		filterByLastNameTxtbx.setValue(null);
		filterByDniIntbx.setValue(null);
	}

	@Listen("onClick = #contratistasSubmenuA4")
	public void navigationToContratistas() {
		if (Path.getComponent("//mainPage/mainWndw") != null) {
			Window mainWndw = (Window) Path.getComponent("//mainPage/mainWndw");
			Events.sendEvent("onGoToContratistas", mainWndw, null);
		}
	}
}

package romaneo.unificado.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zhtml.A;
import org.zkoss.zhtml.Li;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

public class NavigationHistoryServiceImple implements NavigationHistoryService<A, A, Div, Window>, Serializable {

	private static final long serialVersionUID = 659851314105097593L;

	private static int HISTORY_MAX_ITEMS = 10;

	private Map<String, NavigationPage<A, A, Div>> map = new HashMap<String, NavigationPage<A, A, Div>>();
	private List<String> history = new ArrayList<String>();
	private int bottom = 0;
	private int top = 0;
	private int actual = 0;
	private Boolean first = true;
	private Window main;
	private List<A> menues;

	public NavigationHistoryServiceImple() {
		for (int i = 0; i < HISTORY_MAX_ITEMS; i++) {
			history.add(new String());
		}
	}

	@Override
	public void setMain(Window main) {
		this.main = main;
	}

	@Override
	public void setMainButtons(List<A> menues) {
		this.menues = menues;
	}

	@Override
	public Boolean isFirst() {
		return first;
	}

	@Override
	public NavigationPage<A, A, Div> getPage(String name) {
		return map.get(name);
	}

	@Override
	public Boolean disableFoward() {
		return (actual == top);
	}

	@Override
	public Boolean disableBack() {
		return (actual == bottom);
	}

	@Override
	public void addPageToNavigationList(String url, NavigationPage<A, A, Div> page) {
		addPageToNavigationList(url, null, page);
	}

	@Override
	public void addPageToNavigationList(String url, Map<String, Object> arg, NavigationPage<A, A, Div> page) {

		// Deselecciono todos los menues
		unselectAllMenues();

		// Selecciono el menu de la pagina que se va a agregar
		((Li) page.getMenu().getParent()).setSclass("active");

		// Oculto todas las paginas
		hideAllPages();

		Window window = null;
		if (getPage(url) != null) {
			page = getPage(url);
			page.getPage().setVisible(true);
			for (Component comp : page.getPage().getChildren()) {
				if (comp instanceof Window) {
					window = (Window) comp;
					break;
				}
			}
		} else {
			window = (Window) Executions.createComponents(url, main, arg);
			page.getPage().appendChild(window);
			page.getPage().setVisible(true);
		}
		if (arg != null) {
			Events.sendEvent("onArguments", window, arg);
		}

		if (first) {
			first = false;
		} else {
			incrementHistory();
		}

		history.add(actual, url);

		if (map.get(url) == null)
			map.put(url, page);

	}

	private void incrementHistory() {
		if (actual == top) {
			top++;
			if (top >= HISTORY_MAX_ITEMS)
				top = 0;
			if (top == bottom)
				bottom++;
			if (bottom >= HISTORY_MAX_ITEMS)
				bottom = 0;
		}
		incrementActual();
	}

	private void incrementActual() {
		actual++;
		if (actual >= HISTORY_MAX_ITEMS)
			actual = 0;
	}

	private void decrementActual() {
		actual--;
		if (actual < 0)
			actual = HISTORY_MAX_ITEMS - 1;
	}

	@Override
	public NavigationPage<A, A, Div> back() {
		decrementActual();
		return map.get(history.get(actual));
	}

	@Override
	public NavigationPage<A, A, Div> foward() {
		incrementActual();
		return map.get(history.get(actual));
	}

	@Override
	public NavigationPage<A, A, Div> actual() {
		return map.get(history.get(actual));
	}

	@Override
	public void reset() {
		map = new HashMap<String, NavigationPage<A, A, Div>>();
		history = new ArrayList<String>();
		bottom = 0;
		top = 0;
		actual = 0;
		first = true;
	}

	/** Le quita la seleccion a todos los menues */
	private void unselectAllMenues() {
		if (menues != null)
			for (A menu : menues) {
				((Li) menu.getParent()).setSclass("");
			}
	}

	/** Oculta todas las páginas */
	private void hideAllPages() {
		for (String url : map.keySet()) {
			map.get(url).getPage().setVisible(false);
		}
	}

}

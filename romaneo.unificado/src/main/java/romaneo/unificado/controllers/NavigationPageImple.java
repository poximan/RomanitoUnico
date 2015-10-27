package romaneo.unificado.controllers;

import org.zkoss.zhtml.A;
import org.zkoss.zul.Div;

public class NavigationPageImple implements NavigationPage<A, A, Div> {
	
	private A menu;
	private A submenu;
	private Div page;
	private String url;
	
	/**
	 * Constructor
	 * @param menu Menu
	 * @param submenu Submenu (opcional)
	 * @param page página
	 * @param url URL
	 */
	public NavigationPageImple(A menu, A submenu, Div page, String url) {
		super();
		this.menu = menu;
		this.submenu = submenu;
		this.page = page;
		this.url = url;
	}

	@Override
	public String getURL() {
		return url;
	}
	
	@Override
	public A getMenu() {
		return menu;
	}

	@Override
	public A getSubmenu() {
		return submenu;
	}

	@Override
	public Div getPage() {
		return page;
	}

}

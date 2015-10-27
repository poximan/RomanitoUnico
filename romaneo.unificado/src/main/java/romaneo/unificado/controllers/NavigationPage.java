package romaneo.unificado.controllers;

/**
 * Objeto que representa una pagina. Dicha pagina debe estar asociado a un menu y submenu(opcional)
 * @author ehidalgo
 * @param <Menu> Menu
 * @param <Submenu> Submenu
 * @param <Page> Pagina
 */
public interface NavigationPage<Menu, Submenu, Page> {

	/**
	 * URL
	 * @return
	 */
	String getURL();

	/**
	 * Componente menu al cual esta asociado
	 * @return
	 */
	Menu getMenu();

	/**
	 * Componente submenu al cual esta asociado
	 * @return
	 */
	Submenu getSubmenu();

	/**
	 * Componente que representa la página completa
	 * @return
	 */
	Page getPage();

}
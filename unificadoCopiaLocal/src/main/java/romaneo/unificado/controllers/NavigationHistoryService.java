package romaneo.unificado.controllers;

import java.util.List;
import java.util.Map;

import org.zkoss.zhtml.A;
import org.zkoss.zul.Div;

/**
 * Servicio encargado de la lógica de navegacion del usuario
 * @author ehidalgo
 */
public interface NavigationHistoryService<Menu, Submenu, Page, Main> {

	/**
	 * Asignar la pantalla principal
	 * @param main Pantalla principal
	 */
	void setMain(Main main);

	/**
	 * Asignar la lista de botones del menu
	 * @param menues
	 */
	void setMainButtons(List<Menu> menues);

	/**
	 * Bandera para saber si el primer elemento
	 * @return
	 */
	Boolean isFirst();

	/**
	 * Bandera para saber si esta habilitado/deshabilitado navegar hacia adelante
	 * @return
	 */
	Boolean disableFoward();

	/**
	 * Bandera para saber si esta habilitado/deshabilitado navegar hacia atras
	 * @return
	 */
	Boolean disableBack();

	/**
	 * Obtiene una pagina por su URL
	 * @param url URL
	 * @return
	 */
	NavigationPage<Menu, Submenu, Page> getPage(String url);

	/**
	 * Agrega una página a la nacegación
	 * @param url URL de la página (sirve como identificador)
	 * @param page Página
	 */
	void addPageToNavigationList(String url, NavigationPage<Menu, Submenu, Page> page);

	/**
	 * Agrega una página a la nacegación
	 * @param url URL de la página (sirve como identificador)
	 * @param arg Argumentos que se les pasan a la página
	 * @param page Página
	 */
	void addPageToNavigationList(String url, Map<String, Object> arg, NavigationPage<Menu, Submenu, Page> page);

	/**
	 * Página anterior
	 * @return
	 */
	NavigationPage<Menu, Submenu, Page> back();

	/**
	 * Página siguiente
	 * @return
	 */
	NavigationPage<Menu, Submenu, Page> foward();
	
	/**
	 * Página actual
	 * @return
	 */
	NavigationPage<A, A, Div> actual();

	/** Iniciar todo nuevamente */
	void reset();

}

package romaneo.unificado.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.A;
import org.zkoss.zhtml.impl.AbstractTag;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

/**
 * Controller de la página principal
 * 
 * @author hugo
 */
public class MainController extends BaseController
{

	private static final long serialVersionUID = 1L;

	@Wire
	private Include ventana;

	@Wire
	private Window mainWndw;
	@Wire
	private A masterDataBttn, btnNoticias;
	@Wire
	private Div containerDv, interfacesDv, acondicionadorDv, productorDv, establecimientoDv, contratistaDv;
	@Wire
	private Div mobileDeviceDv, messageTypeDv;

	@Wire
	private Iframe noticias;

	@Override
	public String getClassName()
	{
		return null;
	}

	@Override
	protected String getEntityService()
	{
		return null;
	}

	@Override
	protected Window getWindowComponent()
	{
		return mainWndw;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		super.doAfterCompose(comp);

		// Construir el servicio de navegación
		List<A> menus = new ArrayList<>();
		menus.add(masterDataBttn);

		getNavigationHistoryService().setMain(mainWndw);
		getNavigationHistoryService().setMainButtons(menus);

		navigationToAcondicionadores();
	}

	@Listen("onClick = #editAccountBttn")
	public void editAccount()
	{
		alert("Editar cuenta");
	}

	@Listen("onClick = #changePasswordBttn")
	public void changePassword()
	{
		// ((Window)
		// Executions.createComponents(Labels.getLabel("url.changePasswordForm"),
		// null, null)).doModal();
	}

	@Listen("onClick = #logoutBttn")
	public void logout()
	{
		Executions.getCurrent().sendRedirect("/j_spring_security_logout");
	}

	@Listen("onClick = #btnRomaneo")
	public void romaneo()
	{
		containerDv.setVisible(false);
		noticias.setVisible(false);
		ventana.setVisible(true);
		ventana.setSrc("/principal.zul");

	}

	@Listen("onClick = #masterDataBttn")
	public void navigationToMasterData()
	{
		ventana.setVisible(false);
		noticias.setVisible(false);
		containerDv.setVisible(true);
		navigationToAcondicionadores();
	}

	@Listen("onClick = #btnNoticias")
	public void noticias()
	{
		ventana.setVisible(false);
		containerDv.setVisible(false);
		noticias.setVisible(true);
		noticias.setSrc("http://prolana.magyp.gob.ar/");
	}

	// **** MAESTRO DE DATOS **** //

	@Listen("onGoToAcondicionadores = #mainWndw")
	public void navigationToAcondicionadores()
	{
		createWindow(Labels.getLabel("url.acondicionadorList"), acondicionadorDv, masterDataBttn, false, null);
	}

	@Listen("onGoToProductores = #mainWndw")
	public void navigationToProductores()
	{
		createWindow(Labels.getLabel("url.productorList"), productorDv, masterDataBttn, false, null);
	}

	@Listen("onGoToEstablecimientos = #mainWndw")
	public void navigationToEstablecimientos()
	{
		createWindow(Labels.getLabel("url.establecimientoList"), establecimientoDv, masterDataBttn, false, null);
	}

	@Listen("onGoToContratistas = #mainWndw")
	public void navigationToContratistas()
	{
		createWindow(Labels.getLabel("url.contratistaList"), contratistaDv, masterDataBttn, false, null);
	}

	/**
	 * Crear ventana
	 * 
	 * @param url
	 *            Url de la ventana
	 * @param div
	 *            Div que va a contener la ventana
	 * @param button
	 *            Boton al cual se le va a cambiar el estilo a seleccionado
	 *            (opcional)
	 * @param modal
	 *            Bandera para saber si va hacer una ventana modal
	 * @param args
	 *            Argumentos que se le pasa a la ventana
	 */
	private void createWindow(String url, Div div, AbstractTag button, Boolean modal, Map<String, Object> args)
	{
		NavigationPage<A, A, Div> navigationPage = new NavigationPageImple((A) button, null, div, url);
		getNavigationHistoryService().addPageToNavigationList(url, args, navigationPage);
	}
}
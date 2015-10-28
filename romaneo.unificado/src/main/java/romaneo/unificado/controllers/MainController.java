package romaneo.unificado.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.A;
import org.zkoss.zhtml.Span;
import org.zkoss.zhtml.Ul;
import org.zkoss.zhtml.impl.AbstractTag;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Window;

/**
 * Controller de la página principal
 * 
 * @author ehidalgo
 */
public class MainController extends BaseController {

	private static final long serialVersionUID = -816259781842142975L;

	@Wire
	private Window mainWndw;
	@Wire
	private Popup activeTravelsPopup;
	@Wire
	private A interfacesBttn, masterDataBttn;
	@Wire
	private A backBttn, fowardBttn, mailboxBttn, systemManagementBttn;
	@Wire
	private Div containerDv, interfacesDv, acondicionadorDv;
	@Wire
	private Div mobileDeviceDv, messageTypeDv;
	@Wire
	private Ul distributionCentersUl;
	@Wire
	private Label distributionCenterCurrentLbl, unprocessedMessagesLbl;
	@Wire
	private Span unprocessedMessagesSpn;

	@Override
	public String getClassName() {
		return null;
	}

	@Override
	protected String getEntityService() {
		return null;
	}

	@Override
	protected Window getWindowComponent() {
		return mainWndw;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		// Construir el servicio de navegación
		List<A> menus = new ArrayList<>();
		menus.add(masterDataBttn);
		menus.add(interfacesBttn);
		menus.add(systemManagementBttn);

		getNavigationHistoryService().setMain(mainWndw);
		getNavigationHistoryService().setMainButtons(menus);

		navigationToAcondicionadores();
	}

	@Listen("onClick = #editAccountBttn")
	public void editAccount() {
		alert("Editar cuenta");
	}

	@Listen("onClick = #changePasswordBttn")
	public void changePassword() {
		((Window) Executions.createComponents(Labels.getLabel("url.changePasswordForm"), null, null)).doModal();
	}

	@Listen("onClick = #logoutBttn")
	public void logout() {
		Executions.getCurrent().sendRedirect("j_spring_security_logout");
	}

	@Listen("onClick = #systemManagementBttn")
	public void navigationToSystemManagement() {
		navigationToMessageTypes();
	}

	// **** MAESTRO DE DATOS **** //

	@Listen("onGoToAcondicionadores = #mainWndw")
	public void navigationToAcondicionadores() {
		createWindow(Labels.getLabel("url.acondicionadorList"), acondicionadorDv, masterDataBttn, false, null);
	}

	// **** ADMINISTRACION DEL SISTEMA **** //
	@Listen("onGoToMessageTypes = #mainWndw")
	public void navigationToMessageTypes() {
		createWindow(Labels.getLabel("url.messageTypeList"), messageTypeDv, systemManagementBttn, false, null);
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
	private void createWindow(String url, Div div, AbstractTag button, Boolean modal, Map<String, Object> args) {
		NavigationPage<A, A, Div> navigationPage = new NavigationPageImple((A) button, null, div, url);
		getNavigationHistoryService().addPageToNavigationList(url, args, navigationPage);
	}
}
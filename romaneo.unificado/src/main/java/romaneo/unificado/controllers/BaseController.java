package romaneo.unificado.controllers;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.spring.SpringUtil;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.A;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import romaneo.unificado.domain.AppSettings;
import romaneo.unificado.services.UsuarioService;
import romaneo.unificado.services.BaseService;
import romaneo.unificado.services.AcondicionadorService;
import romaneo.unificado.services.LocalidadService;

public abstract class BaseController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	public static final String LIST_COMPONENT = "listComponent";
	public static final String SELECTED = "selected";
	public static final String ENTITY = "entity";
	public static final String LIST = "list";

	protected Label distributionCenterCurrentLbl;

	@SuppressWarnings("rawtypes")
	private BaseService service = null;
	private NavigationHistoryService<A, A, Div, Window> navigationHistoryService = new NavigationHistoryServiceImple();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	@SuppressWarnings("rawtypes")
	public BaseController() {
		super();

		// Obtengo el userService del contexto de Spring
		if (getEntityService() != null) {
			setService((BaseService) SpringUtil.getBean(getEntityService()));
		}
		final Desktop desktop = Executions.getCurrent().getDesktop();
		if (!desktop.isServerPushEnabled()) {
			desktop.enableServerPush(true);
		}
	}

	protected void showMessage(String msg) {
		showMessage(msg, null);
	}

	protected void showMessage(String msg, Exception ex) {
		Messagebox.show(msg + (ex == null ? "" : ex.getMessage()));
		if (ex != null) {
			ex.printStackTrace();
		}
	}

	public String formatPhone(String customerPhone) {
		String phone = "";
		Pattern p = Pattern.compile("549([0-9]+)15([0-9]+)");
		Matcher m = p.matcher(customerPhone);
		if (m.matches()) {
			phone = "0" + m.group(1) + " 15" + m.group(2);
		}
		return phone;
	}

	public abstract String getClassName();

	protected abstract String getEntityService();

	protected abstract Window getWindowComponent();

	@SuppressWarnings("rawtypes")
	protected void setService(BaseService service) {
		this.service = service;
	}

	@SuppressWarnings("rawtypes")
	protected BaseService getService() {
		return service;
	}

	public Date timeToZero(Date date) {
		Calendar cal = Calendar.getInstance(); // get calendar instance
		cal.setTime(date); // set cal to date
		cal.set(Calendar.HOUR_OF_DAY, 0); // set hour to midnight
		cal.set(Calendar.MINUTE, 0); // set minute in hour
		cal.set(Calendar.SECOND, 0); // set second in minute
		cal.set(Calendar.MILLISECOND, 0); // set millis in second
		return cal.getTime();
	}

	public Date timeToFull(Date date) {
		Calendar cal = Calendar.getInstance(); // get calendar instance
		cal.setTime(date); // set cal to date
		cal.set(Calendar.HOUR_OF_DAY, 23); // set hour to midnight
		cal.set(Calendar.MINUTE, 59); // set minute in hour
		cal.set(Calendar.SECOND, 59); // set second in minute
		cal.set(Calendar.MILLISECOND, 999); // set millis in second
		return cal.getTime();
	}

	@Command
	public void exportListboxToExcel(@BindingParam("ref") Listbox listbox) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		AMedia amedia = new AMedia("FirstReport.xlsx", "xls", "application/file", out.toByteArray());
		Filedownload.save(amedia);
		out.close();
	}

	// SERVICES

	/**
	 * @return el servicio de que contiene las configuraciones de la aplicación.
	 */
	protected AppSettings getAppSettings() {
		return (AppSettings) SpringUtil.getBean(AppSettings.class.getSimpleName());
	}

	/** @return el servicio de usuarios. */
	protected UsuarioService getUsuarioService() {
		return (UsuarioService) SpringUtil.getBean(UsuarioService.class.getSimpleName());
	}

	/** @return el servicio de choferes. */
	protected AcondicionadorService getAcondicionadorService() {
		return (AcondicionadorService) SpringUtil.getBean(AcondicionadorService.class.getSimpleName());
	}

	/** @return el servicio de localidades. */
	protected LocalidadService getLocalidadService() {
		return (LocalidadService) SpringUtil.getBean(LocalidadService.class.getSimpleName());
	}

	/** @return el servicio de navegacion del usuario. */
	protected NavigationHistoryService<A, A, Div, Window> getNavigationHistoryService() {
		return navigationHistoryService;
	}

	protected void addMarker(String mapName, Float lon, Float lat) {
		String jsCommand = mapName + ".addMarker(" + lon + "," + lat + ")";
		Clients.evalJavaScript(jsCommand);
	}

	protected void removeAllLayers(String mapName) {
		String jsCommand = mapName + ".removeAllLayers()";
		Clients.evalJavaScript(jsCommand);
	}

	protected void removeLayersByName(String mapName, String layerName) {
		String jsCommand = mapName + ".removeLayer(\"" + layerName + "\")";
		Clients.evalJavaScript(jsCommand);
	}

	protected void zoomToAllLayers(String mapName) {
		String jsCommand = mapName + ".zoomToAllLayers()";
		Clients.evalJavaScript(jsCommand);
	}
}

package romaneo.unificado.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.A;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

import romaneo.unificado.domain.AppSettings;
import romaneo.unificado.services.UsuarioService;
import romaneo.unificado.services.acondicionador.AcondicionadorService;
import romaneo.unificado.services.contratista.ContratistaService;
import romaneo.unificado.services.establecimiento.EstablecimientoService;
import romaneo.unificado.services.productor.ProductorService;
import romaneo.unificado.services.BaseService;
import romaneo.unificado.services.ContactoService;
import romaneo.unificado.services.EstadoService;
import romaneo.unificado.services.LocalidadService;
import romaneo.unificado.services.MessageService;
import romaneo.unificado.services.PersonaService;

public abstract class BaseController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	public static final String LIST_COMPONENT = "listComponent";
	public static final String SELECTED = "selected";
	public static final String ENTITY = "entity";
	public static final String LIST = "list";

	@SuppressWarnings("rawtypes")
	private BaseService service = null;

	private NavigationHistoryService<A, A, Div, Window> navigationHistoryService = new NavigationHistoryServiceImple();

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

	// SERVICES

	/**
	 * @return el servicio de que contiene las configuraciones de la aplicación.
	 */
	protected AppSettings getAppSettings() {
		return (AppSettings) SpringUtil.getBean(AppSettings.class.getSimpleName());
	}

	/**
	 * @return el servicio de persona.
	 */
	protected PersonaService getPersonaService() {
		return (PersonaService) SpringUtil.getBean(PersonaService.class.getSimpleName());
	}

	/**
	 * @return el servicio de usuario.
	 */
	protected UsuarioService getUsuarioService() {
		return (UsuarioService) SpringUtil.getBean(UsuarioService.class.getSimpleName());
	}

	/**
	 * @return el servicio de usuario.
	 */
	protected ContactoService getContactoService() {
		return (ContactoService) SpringUtil.getBean(ContactoService.class.getSimpleName());
	}

	/**
	 * @return el servicio de usuario.
	 */
	protected EstadoService getEstadoService() {
		return (EstadoService) SpringUtil.getBean(EstadoService.class.getSimpleName());
	}

	/**
	 * @return el servicio de acondicionadores.
	 */
	protected AcondicionadorService getAcondicionadorService() {
		return (AcondicionadorService) SpringUtil.getBean(AcondicionadorService.class.getSimpleName());
	}

	/**
	 * @return el servicio de productores.
	 */
	protected ProductorService getProductorService() {
		return (ProductorService) SpringUtil.getBean(ProductorService.class.getSimpleName());
	}

	/**
	 * @return el servicio de establecimientos.
	 */
	protected EstablecimientoService getEstablecimientoService() {
		return (EstablecimientoService) SpringUtil.getBean(EstablecimientoService.class.getSimpleName());
	}

	/**
	 * @return el servicio de contratistas.
	 */
	protected ContratistaService getContratistaService() {
		return (ContratistaService) SpringUtil.getBean(ContratistaService.class.getSimpleName());
	}

	/**
	 * @return el servicio de localidades.
	 */
	protected LocalidadService getLocalidadService() {
		return (LocalidadService) SpringUtil.getBean(LocalidadService.class.getSimpleName());
	}

	/**
	 * @return el servicio de mensajes.
	 */
	protected MessageService getMessageService() {
		return (MessageService) SpringUtil.getBean(MessageService.class.getSimpleName());
	}

	/**
	 * @return el servicio de navegacion del usuario.
	 */
	protected NavigationHistoryService<A, A, Div, Window> getNavigationHistoryService() {
		return navigationHistoryService;
	}

}

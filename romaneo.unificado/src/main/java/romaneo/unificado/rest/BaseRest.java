package romaneo.unificado.rest;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;

import romaneo.unificado.domain.AppSettings;
import romaneo.unificado.services.UsuarioService;
import romaneo.unificado.services.acondicionador.AcondicionadorService;
import romaneo.unificado.services.contratista.ContratistaService;
import romaneo.unificado.services.establecimiento.EstablecimientoService;
import romaneo.unificado.services.productor.ProductorService;
import romaneo.unificado.services.BaseService;
import romaneo.unificado.services.LocalidadService;

public abstract class BaseRest {

	public static final String LIST_COMPONENT = "listComponent";
	public static final String SELECTED = "selected";
	public static final String ENTITY = "entity";
	public static final String LIST = "list";

	@SuppressWarnings("rawtypes")
	private BaseService service = null;

	@SuppressWarnings("rawtypes")
	public BaseRest() {

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

	public abstract String getClassName();

	protected abstract String getEntityService();

	@SuppressWarnings("rawtypes")
	protected void setService(BaseService service) {
		this.service = service;
	}

	@SuppressWarnings("rawtypes")
	protected BaseService getService() {
		return service;
	}

	// SERVICES

	/**
	 * @return el servicio de que contiene las configuraciones de la aplicación.
	 */
	protected AppSettings getAppSettings() {
		return (AppSettings) SpringUtil.getBean(AppSettings.class.getSimpleName());
	}

	/**
	 * @return el servicio de usuario.
	 */
	protected UsuarioService getUsuarioService() {
		return (UsuarioService) SpringUtil.getBean(UsuarioService.class.getSimpleName());
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
}

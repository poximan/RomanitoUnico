package romaneo.unificado.rest;

import org.zkoss.spring.SpringUtil;

import romaneo.unificado.services.BaseService;
import romaneo.unificado.services.MessageService;
import romaneo.unificado.services.UsuarioMovilService;

public abstract class BaseRest {

	@SuppressWarnings("rawtypes")
	private BaseService service = null;

	@SuppressWarnings("rawtypes")
	public BaseRest() {

		super();

		// Obtengo el userService del contexto de Spring
		if (getEntityService() != null) {
			setService((BaseService) SpringUtil.getBean(getEntityService()));
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
	 * @return el servicio de Usuarios con movil.
	 */
	protected UsuarioMovilService getUsuarioMovilService() {
		return (UsuarioMovilService) SpringUtil.getBean(UsuarioMovilService.class.getSimpleName());
	}

	/**
	 * @return el servicio de mensajes.
	 */
	protected MessageService getMessageService() {
		return (MessageService) SpringUtil.getBean(MessageService.class.getSimpleName());
	}
}

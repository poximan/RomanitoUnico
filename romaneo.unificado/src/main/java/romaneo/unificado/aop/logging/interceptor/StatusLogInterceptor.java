package romaneo.unificado.aop.logging.interceptor;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import romaneo.unificado.domain.Logging;
import romaneo.unificado.services.UsuarioService;
import romaneo.unificado.services.LoggingService;
import romaneo.unificado.stateMachine.ObjectWithState;
import romaneo.unificado.stateMachine.State;
import romaneo.unificado.stateMachine.StateEvent;

/**
 * Intercepta la funcion fire de la clase State y crea el log correspondiente
 * 
 * @author ehidalgo
 */
public class StatusLogInterceptor implements MethodInterceptor {

	private UsuarioService usuarioService;
	private LoggingService loggingService;

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setLoggingService(LoggingService loggingService) {
		this.loggingService = loggingService;
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {

		// Obtengo los parametros de la funcion
		StateEvent event = (StateEvent) methodInvocation.getArguments()[0];
		ObjectWithState object = (ObjectWithState) methodInvocation.getArguments()[1];
		String observation = methodInvocation.getArguments().length == 3 ? (String) methodInvocation.getArguments()[2]
				: null;

		// Guardo el estado previo
		String previousState = object.getStatus();

		// Que el metodo haga lo que tenga que hacer
		State newState = (State) methodInvocation.proceed();

		try {

			// Creo el log
			Logging logging = new Logging();

			logging.setTypeObject("table");
			logging.setNameObject(object.getClass().getSimpleName().toLowerCase());
			logging.setUserId(usuarioService.getLoguedUser() != null ? usuarioService.getLoguedUser().getId() : null);

			logging.setIdObject(object.getId());
			logging.setStateFrom(previousState);
			logging.setEvent(event.getName());
			logging.setStateTo(newState.getName());
			logging.setObservations(observation);
			logging.setLogDateTime(new Date());

			loggingService.create(logging);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return newState;
	}

}

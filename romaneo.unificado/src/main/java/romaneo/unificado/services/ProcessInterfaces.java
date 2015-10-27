package romaneo.unificado.services;

import org.springframework.core.task.TaskExecutor;

public interface ProcessInterfaces {
	
	/**
	 * Servicio para crear las interfaces en SCAM
	 * @param interfaceService Servicio encargado de la logica de las interfaces
	 */
	void setInterfaceService(InterfaceService interfaceService);
	
	/**
	 * Tipos de interfaces a procesar
	 * @param interfaceType Tipo de interface
	 */
	void setInterfaceType(String interfaceType);
	
	/**
	 * Planificador de tareas para procesar cada interface en un thread
	 * @param taskExecutor Planificador de tareas
	 */
	void setTaskExecutor(TaskExecutor taskExecutor);

	/**
	 * Procesar interfaces
	 */
	void process();

}

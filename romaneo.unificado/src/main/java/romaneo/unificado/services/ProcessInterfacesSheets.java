package romaneo.unificado.services;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import romaneo.unificado.domain.Interface;

/**
 * Procesa las interfaces del tipo PLANILLAS de la empresa Perren
 * 
 * @author ehidalgo
 */
public class ProcessInterfacesSheets implements ProcessInterfaces {

	protected static final Logger logger = Logger.getLogger(ProcessInterfacesSheets.class);

	private InterfaceService interfaceService;
	private String interfaceType;
	private TaskExecutor taskExecutor;

	@Override
	public void setInterfaceService(InterfaceService interfaceService) {
		this.interfaceService = interfaceService;
	}

	@Override
	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	@Override
	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	@Override
	public void process() {

		// logger.info("----- Cron para procesar interfaces de planillas
		// -----");

		for (Interface interfac : interfaceService.findUnprocessedAndAutoupload(interfaceType)) {

			// logger.info("Interface a procesar -> " + interfac.getFilename());
			interfac.setProcessStartDate(new Date());
			interfac.setProcessStopDate(null);

			interfaceService.update(interfac);
			taskExecutor.execute(new ProcessInterfaceTask(interfaceService, interfac));
		}
	}

	private class ProcessInterfaceTask implements Runnable {

		private InterfaceService interfaceService;
		private Interface interfac;

		public ProcessInterfaceTask(InterfaceService interfaceService, Interface interfac) {
			super();
			this.interfac = interfac;
			this.interfaceService = interfaceService;
		}

		@Override
		public void run() {
			try {
				interfaceService.processFile(interfac, true, null);
			} catch (Exception e) {

				interfac.setProcessStartDate(null);
				interfaceService.update(interfac);

				e.printStackTrace();
				Thread t = Thread.currentThread();
				t.getUncaughtExceptionHandler().uncaughtException(t, e);
			}
		}

	}

}

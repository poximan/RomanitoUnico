package romaneo.unificado.services;

import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.services.AdUserService;
import romaneo.unificado.services.LocalidadService;

import romaneo.unificado.domain.Interface;

public class InterfaceSheetsServiceImple extends InterfaceGenericServiceImple implements InterfaceService {

	private AdUserService adUserService;
	private LocalidadService localidadService;

	@Transactional(rollbackFor = Exception.class)
	public InterfaceResult processFile(Interface interfaz, Boolean notifyByEmail, ProgressListener progress)
			throws Exception {

		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setExtractorLine(InterfaceExtractorLineData extractorLine) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendEmail(Interface interfaz) {
		// TODO Auto-generated method stub
	}

	public AdUserService getAdUserService() {
		return adUserService;
	}

	public LocalidadService getLocalidadService() {
		return localidadService;
	}

	public void setAdUserService(AdUserService adUserService) {
		this.adUserService = adUserService;
	}

	public void setLocalidadService(LocalidadService localidadService) {
		this.localidadService = localidadService;
	}

}
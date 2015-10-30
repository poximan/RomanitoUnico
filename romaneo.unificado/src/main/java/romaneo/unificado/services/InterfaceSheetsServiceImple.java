package romaneo.unificado.services;

import org.springframework.transaction.annotation.Transactional;

import romaneo.unificado.services.UsuarioService;
import romaneo.unificado.services.LocalidadService;

import romaneo.unificado.domain.Interface;

public class InterfaceSheetsServiceImple extends InterfaceGenericServiceImple implements InterfaceService {

	private UsuarioService usuarioService;
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

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public LocalidadService getLocalidadService() {
		return localidadService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void setLocalidadService(LocalidadService localidadService) {
		this.localidadService = localidadService;
	}

}
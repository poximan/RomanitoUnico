package romaneo.unificado.controllers;

import org.springframework.security.core.userdetails.User;
import org.zkoss.spring.SpringUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.domain.Usuario;
import romaneo.unificado.exceptions.FieldResourceError;
import romaneo.unificado.exceptions.ValidationException;
import romaneo.unificado.services.UsuarioService;

/**
 * Controller para el cambio de contraseña
 * 
 * @author ehidalgo
 */
public class ChangePasswordFormController extends BaseController {

	private static final long serialVersionUID = 1L;

	@Wire
	private Window changePasswordFormWndw;
	@Wire
	private Textbox passwordTxtbx, newPasswordTxtbx, reNewPasswordTxtbx;

	@Override
	protected String getEntityService() {
		return UsuarioService.class.getSimpleName();
	}

	@Override
	protected Window getWindowComponent() {
		return changePasswordFormWndw;
	}

	@Override
	public String getClassName() {
		return Usuario.class.getName();
	}

	@Listen("onClick = #acceptBttn")
	public void accept(Event event) {
		try {
			User userSpring = ((User) org.springframework.security.core.context.SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal());
			((UsuarioService) SpringUtil.getBean(UsuarioService.class.getSimpleName())).changePassword(
					userSpring.getUsername(), passwordTxtbx.getValue(), newPasswordTxtbx.getValue(),
					reNewPasswordTxtbx.getValue());
			getWindowComponent().onClose();
		} catch (ValidationException e) {
			for (FieldResourceError error : e.getError().getFieldErrors()) {
				if (error.getField().equalsIgnoreCase("password")) {
					passwordTxtbx.setErrorMessage(error.getMessage());
				}
			}
		} catch (Exception e) {
			Messagebox.show("Hubo un error desconocido. Intentelo nuevamente mas tarde", Labels.getLabel("app.error"),
					Messagebox.OK, Messagebox.ERROR);
		}
	}

	@Listen("onClick = #cancelBttn")
	public void cancel() {
		getWindowComponent().onClose();
	}
}

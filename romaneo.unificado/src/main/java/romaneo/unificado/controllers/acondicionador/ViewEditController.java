package romaneo.unificado.controllers.acondicionador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.controllers.BaseFormController;
import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.services.acondicionador.AcondicionadorService;

public class ViewEditController extends BaseFormController {

	private static final long serialVersionUID = 1L;

	private Acondicionador acondicionador;

	@Wire
	private Window acondicionadorFormWndw;

	@Wire
	Textbox nombreTxtbx;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		acondicionador = (Acondicionador) Sessions.getCurrent().getAttribute("selected");

		/*
		 * el metodo find lo vas a encontrar en BaseService(), el parametro de
		 * la funcion Entity find(Object key) se castea segun la implementacion,
		 * en el caso de acondicionador, key termina siendo un Integer
		 */
		Integer un_id_conocido_de_acondicionador = new Integer(1);
		getService().find(un_id_conocido_de_acondicionador);
	}

	@Listen("onClick = #btnGuardar")
	public void guardar() {
		nombreTxtbx.setText("chupalaaaaaa..!");
		System.out.println("btnGuardar...!");
	}

	@Listen("onClick = #btnEditar")
	public void editar() {
		System.out.println("btnEditar...!");
	}

	@Listen("onClick = #btnVolver")
	public void volver() {
		System.out.println("btnVolver...!");
	}

	@Override
	public String getClassName() {
		return Acondicionador.class.getName();
	}

	@Override
	protected String getEntityService() {
		return AcondicionadorService.class.getSimpleName();
	}

	@Override
	protected Window getWindowComponent() {
		return acondicionadorFormWndw;
	}
}

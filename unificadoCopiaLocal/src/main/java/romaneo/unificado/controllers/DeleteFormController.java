package romaneo.unificado.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import romaneo.unificado.domain.BaseEntity;
import romaneo.unificado.services.BaseService;

/**
 * Controller de eliminar
 * 
 * @author ehidalgo
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class DeleteFormController extends BaseController {

	private static final long serialVersionUID = 1L;

	public static final String ARG_SELECTED = "selected";
	public static final String ARG_SERVICE = "service";

	@Wire
	private Window deleteFormWndw;

	private BaseService service = null;
	private BaseEntity entity = null;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		entity = (BaseEntity) Executions.getCurrent().getArg().get(ARG_SELECTED);
		service = (BaseService) Executions.getCurrent().getArg().get(ARG_SERVICE);
	}

	protected Window getWindowComponent() {
		return deleteFormWndw;
	}

	@Listen("onClick = #acceptBttn")
	public void accept() {
		try {
			service.delete(entity);
			Events.sendEvent("onRefresh", getWindowComponent().getParent(), null);
			getWindowComponent().onClose();
		} catch (DataIntegrityViolationException e) {
			// Tiene referencias a otras entidades
			Messagebox.show(Labels.getLabel("app.dataIntegrityViolationException"),
					Labels.getLabel("app.failedToRemove"), Messagebox.OK, Messagebox.ERROR);
			getWindowComponent().onClose();
		} catch (Exception e) {
			// Otro error
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
			getWindowComponent().onClose();
		}
	}

	@Listen("onClick = #cancelBttn")
	public void cancel() {
		getWindowComponent().onClose();
	}

	@Override
	public String getClassName() {
		return null;
	}

	@Override
	protected String getEntityService() {
		return null;
	}

}

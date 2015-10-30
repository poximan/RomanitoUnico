package romaneo.unificado.controllers;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.domain.MessageType;
import romaneo.unificado.services.MessageTypeService;

public class MessageTypeFormController extends BaseFormController {

	private static final long serialVersionUID = 1L;

	@Wire
	private Window messageTypeFormWndw;
	@Wire
	private Textbox nameTxtbx, procedureTxtbx;

	private MessageType messageType;

	@Override
	public String getClassName() {
		return MessageType.class.getName();
	}

	@Override
	protected String getEntityService() {
		return MessageTypeService.class.getSimpleName();
	}

	@Override
	protected Window getWindowComponent() {
		return messageTypeFormWndw;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		messageType = (MessageType) Executions.getCurrent().getArg().get(SELECTED);

		if (messageType == null)
			return;

		nameTxtbx.setValue(Labels.getLabel("mailbox.messageType." + messageType.getName()));
		procedureTxtbx.setValue(messageType.getProcedure());
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #acceptBttn")
	@Override
	public void accept() {
		messageType.setProcedure(procedureTxtbx.getValue());
		try {
			getService().update(messageType);

			// Refrescar la lista
			Events.sendEvent("onRefresh", getWindowComponent().getParent(), null);
			getWindowComponent().onClose();

		} catch (Exception e) {
			// Otro error
			Messagebox.show(e.getMessage(), "Error", Messagebox.OK, Messagebox.ERROR);
		}

	}

}

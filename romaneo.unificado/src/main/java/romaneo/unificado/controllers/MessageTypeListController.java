package romaneo.unificado.controllers;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Window;

import romaneo.unificado.domain.MessageType;
import romaneo.unificado.services.MessageTypeService;

public class MessageTypeListController extends BasePagedListController<MessageType> {

	private static final long serialVersionUID = 8786850921227139645L;

	@Wire
	private Window messageTypesListWndw;
	@Wire
	private Listbox messageTypesLstbx;
	@Wire
	private Paging messageTypesPgn;
	@Wire
	private Combobox messageTypesPageSizeCmbbx;

	@Override
	protected Listbox getListComponent() {
		return messageTypesLstbx;
	}

	@Override
	protected Paging getPagingComponent() {
		return messageTypesPgn;
	}

	@Override
	protected Combobox getPageSizeComponent() {
		return messageTypesPageSizeCmbbx;
	}

	@Override
	protected ListitemRenderer<MessageType> getListitemRender() {
		return new ListitemRenderer<MessageType>() {
			@Override
			public void render(Listitem item, MessageType data, int arg) throws Exception {
				(new Listcell("" + Labels.getLabel("mailbox.messageType." + data.getName()))).setParent(item);
				(new Listcell("" + (data.getProcedure() != null ? data.getProcedure() : ""))).setParent(item);
				item.setAttribute(ENTITY, data);
			}
		};
	}

	@Override
	protected String getFormPageName() {
		return Labels.getLabel("url.messageTypeForm");
	}

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
		return messageTypesListWndw;
	}

}

package romaneo.unificado.controllers.acondicionador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import romaneo.unificado.domain.Acondicionador;

public class ViewEditController extends SelectorComposer<Component>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Acondicionador acondicionador;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception
	{
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		acondicionador = (Acondicionador) Sessions.getCurrent().getAttribute("selected");
		System.out.print("id: "+Executions.getCurrent().getParameter("id"));
		
	}
	
	@Wire
	Textbox nombreTxtbx;
	
	
	@Listen("onClick = #btnGuardar")
	public void guardar()
	{
		nombreTxtbx.setText("chupalaaaaaa..!");
		System.out.println("btnGuardar...!");
	}
	
	@Listen("onClick = #btnEditar")
	public void editar()
	{
		System.out.println("btnEditar...!");
	}
	
	@Listen("onClick = #btnVolver")
	public void volver()
	{
		System.out.println("btnVolver...!");
	}
	
}

package romaneo.unificado.controllers;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;


public class WindowsController extends SelectorComposer<Component>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Wire
	private Include ventana;
	@Wire
	private Button boton;

	@Listen("onClick = #noticias")
	public void noticias()
	{
		Window window = (Window) Path.getComponent("//indexPage/window");
		Include frame = (Include) window.getFellowIfAny("ventana");
		frame.setVisible(false);
		Iframe extPage = (Iframe) window.getFellowIfAny("extPage");
		extPage.setVisible(true);
		extPage.setSrc("http://prolana.magyp.gob.ar/");
	}
	
	@Listen("onClick = #buscar")
	public void buscar()
	{
		System.out.print("busqueda");
		Window window = (Window) Path.getComponent("//indexPage/window");
		Iframe extPage = (Iframe) window.getFellowIfAny("extPage");
		extPage.setVisible(false);
		Include frame = (Include) window.getFellowIfAny("ventana");
		frame.setVisible(true);
		frame.setSrc("/WEB-INF/zul/busquedaAcondicionador.zul");
	}
	
	@Listen("onClick = #btnIniciar")
	public void iniciar()
	{
	}
}

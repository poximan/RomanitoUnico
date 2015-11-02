package romaneo.unificado.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

public class WindowsController extends SelectorComposer<Component> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Include ventana;
	@Wire
	private A boton;

	@Listen("onClick = #btnRomaneo")
	public void romaneo() {
		System.out.println("Romaneo");
	}

	@Listen("onClick = #btnNoticias")
	public void noticias() {
		Window window = (Window) Path.getComponent("//indexPage/window");
		Include frame = (Include) window.getFellowIfAny("ventana");
		frame.setVisible(false);
		Iframe extPage = (Iframe) window.getFellowIfAny("extPage");
		extPage.setVisible(true);
		extPage.setSrc("http://prolana.magyp.gob.ar/");
	}


}

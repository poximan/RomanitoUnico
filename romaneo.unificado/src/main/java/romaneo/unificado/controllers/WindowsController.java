package romaneo.unificado.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;

public class WindowsController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;

	@Wire
	private Include romaneo;

	@Wire
	private Iframe noticias;

	@Listen("onClick = #romaneoBttn")
	public void romaneo() {

		romaneo.setSrc("general.zul");
		romaneo.setVisible(true);

		noticias.setVisible(false);
	}

	@Listen("onClick = #noticiasBttn")
	public void noticias() {

		romaneo.setVisible(false);

		noticias.setVisible(true);
		noticias.setSrc("http://prolana.magyp.gob.ar/");
	}

}

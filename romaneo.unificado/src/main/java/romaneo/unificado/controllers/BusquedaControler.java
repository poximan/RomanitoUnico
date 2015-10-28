package romaneo.unificado.controllers;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import romaneo.unificado.domain.Acondicionador;
import romaneo.unificado.services.AcondicionadorService;
import romaneo.unificado.services.AcondicionadorServiceImple;

public class BusquedaControler extends SelectorComposer<Component> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Textbox textNombre;
	@Wire
	private Textbox textApellido;
	@Wire
	private Textbox textDni;
	@Wire
	private Textbox textDireccion;
	@Wire
	private Listbox aconListbox;

	@Override
	public void doAfterCompose(Component comp) throws Exception {

		super.doAfterCompose(comp);
		System.out.println("entrada a la clase");
	}

	/*
	 * setea la lista con 25 filas
	 */
	@Listen("onClick = #btnVC")
	public void vc() {
		System.out.println("metodo vc");
		aconListbox.setPageSize(25);
	}

	/*
	 * setea la lista con 50 filas
	 */
	@Listen("onClick = #btnCin")
	public void cincuenta() {
		System.out.println("metodo cincuenta");
		aconListbox.setPageSize(50);
	}

	/*
	 * setea la lista con 10 filas
	 */
	@Listen("onClick = #btnDiez")
	public void diez() {
		System.out.println("metodo diez");
		aconListbox.setPageSize(10);
	}

	@Listen("onClick = #btnBuscar")
	public void buscar() {
		System.out.println("metodo buscar");
		AcondicionadorService serv_acondicionador = new AcondicionadorServiceImple();

		List<Acondicionador> acondicionadores = serv_acondicionador.findAll();

		aconListbox.setModel(new ListModelList<Acondicionador>(acondicionadores));
	}
}

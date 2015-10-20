package romaneo.unificado.controllers;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import romaneo.unificado.entities.Acondicionador;
import romaneo.unificado.services.AcondicionadorService;

public class BusquedaControler extends SelectorComposer<Component>
{
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

	@Listen("onClick = #btnBuscar")
	public void buscar()
	{
		List<Acondicionador> acondicionadores = AcondicionadorService.allAcondicionadores();
		/*
		if(textNombre.getText() != "")
			acondicionadores.addAll(AcondicionadorService.buscarNombre(textNombre.getText()));
		if(textApellido.getText() != "")
			acondicionadores.addAll(AcondicionadorService.bucarApellido(textApellido.getText()));
        
        */
		aconListbox.setModel(new ListModelList<Acondicionador>(acondicionadores));
	}
}

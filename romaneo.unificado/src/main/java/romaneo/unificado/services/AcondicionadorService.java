package romaneo.unificado.services;

import java.util.ArrayList;
import java.util.List;

import romaneo.unificado.entities.Acondicionador;
import romaneo.unificado.entities.Contacto;
import romaneo.unificado.entities.Persona;

public class AcondicionadorService
{
	public static List<Acondicionador> allAcondicionadores()
	{
		List<Acondicionador> acondicionadores = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			Contacto contacto = new Contacto("email" + i + "@hotmail.com", "456486" + i);
			Persona persona = new Persona("Nombre " + i, "Apellido " + i, i);

			Acondicionador acondicionador = new Acondicionador(persona, contacto);

			acondicionadores.add(acondicionador);
		}
		
		return acondicionadores;
	}
	
	public static List<Acondicionador> buscarNombre(String text)
	{
		return null;
	}

	public static List<Acondicionador> bucarApellido(String text)
	{
		return null;
	}

}

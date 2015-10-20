package romaneo.unificado.services;

import java.util.ArrayList;
import java.util.List;

import entidades.Acondicionador;

public class AcondicionadorService
{

	public static List<Acondicionador> allAcondicionadores()
	{
		List<Acondicionador> acondicionadores = new ArrayList<Acondicionador>();
		acondicionadores.add(new Acondicionador("Jorge", "Di Pasqua", "37327466", "jenkins 37"));
		acondicionadores.add(new Acondicionador("Hugo", "Donato", "29283223", "fente al aeropueto"));
		acondicionadores.add(new Acondicionador("Felipe", "Carou", "34922443", "Morgan"));
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

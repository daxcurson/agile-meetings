package agilemeetings.service;

import java.util.List;

import agilemeetings.exceptions.PersonaExistenteException;
import agilemeetings.model.Persona;

public interface PersonaService 
{
	public Persona getPersonaById(int id);
	public List<Persona> listarPersonas();
	public void agregarPersona(Persona p) throws PersonaExistenteException;
	public void grabarPersona(Persona p) throws PersonaExistenteException;
}

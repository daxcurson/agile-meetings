package agilemeetings.service;

import java.util.List;

import agilemeetings.model.Persona;

public interface PersonaService 
{
	public Persona getPersonaById(int id);
	public List<Persona> listarPersonas();
}

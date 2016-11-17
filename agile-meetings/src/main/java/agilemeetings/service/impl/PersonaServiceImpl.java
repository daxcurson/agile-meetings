package agilemeetings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.PersonaDAO;
import agilemeetings.model.Persona;
import agilemeetings.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService
{
	@Autowired
	private PersonaDAO personaDAO;

	@Override
	public Persona getPersonaById(int id) 
	{
		return personaDAO.getById(id);
	}

	@Override
	public List<Persona> listarPersonas() 
	{
		return personaDAO.listarPersonas();
	}

}

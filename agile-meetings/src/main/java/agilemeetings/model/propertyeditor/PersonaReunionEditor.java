package agilemeetings.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import agilemeetings.model.Persona;
import agilemeetings.model.PersonaReunion;
import agilemeetings.service.PersonaService;

public class PersonaReunionEditor extends PropertyEditorSupport 
{

	private final PersonaService personaService;
	public PersonaReunionEditor(PersonaService personaService) 
	{
		this.personaService=personaService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		PersonaReunion p=new PersonaReunion();
		Persona per=personaService.getPersonaById(Integer.parseInt(text));
		p.setPersona(per);
        setValue(p);
	}

}

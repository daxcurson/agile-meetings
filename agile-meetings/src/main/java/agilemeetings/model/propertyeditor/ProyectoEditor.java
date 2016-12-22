package agilemeetings.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import agilemeetings.model.Proyecto;
import agilemeetings.service.ProyectoService;

public class ProyectoEditor extends PropertyEditorSupport 
{

	private final ProyectoService proyectoService;
	public ProyectoEditor(ProyectoService proyectoService) 
	{
		this.proyectoService=proyectoService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		Proyecto p=proyectoService.getProyectoById(Integer.parseInt(text));
        setValue(p);
	}

}

package agilemeetings.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import agilemeetings.model.EstadoProyecto;
import agilemeetings.service.ProyectoService;

public class EstadoProyectoEditor extends PropertyEditorSupport {

	private final ProyectoService proyectoService;
	public EstadoProyectoEditor(ProyectoService proyectoService) 
	{
		this.proyectoService=proyectoService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		EstadoProyecto p=proyectoService.getEstadoProyectoById(Integer.parseInt(text));
        setValue(p);
	}
}

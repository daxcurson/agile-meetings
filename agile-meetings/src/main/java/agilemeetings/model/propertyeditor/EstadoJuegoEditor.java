package agilemeetings.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import agilemeetings.model.EstadoJuego;
import agilemeetings.service.JuegoService;

public class EstadoJuegoEditor extends PropertyEditorSupport 
{

	private final JuegoService juegoService;
	public EstadoJuegoEditor(JuegoService juegoService) 
	{
		this.juegoService=juegoService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		EstadoJuego p=juegoService.getEstadoJuegoById(Integer.parseInt(text));
        setValue(p);
	}
}

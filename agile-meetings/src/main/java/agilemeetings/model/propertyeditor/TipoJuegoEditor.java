package agilemeetings.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import agilemeetings.model.TipoJuego;
import agilemeetings.service.JuegoService;

public class TipoJuegoEditor extends PropertyEditorSupport
{
	private final JuegoService juegoService;
	public TipoJuegoEditor(JuegoService juegoService) 
	{
		this.juegoService=juegoService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		TipoJuego p=juegoService.getTipoJuegoById(Integer.parseInt(text));
        setValue(p);
	}

}

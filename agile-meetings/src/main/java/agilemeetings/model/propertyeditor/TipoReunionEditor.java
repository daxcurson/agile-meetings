package agilemeetings.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import agilemeetings.model.TipoReunion;
import agilemeetings.service.ReunionService;

public class TipoReunionEditor extends PropertyEditorSupport 
{

	private final ReunionService reunionService;
	public TipoReunionEditor(ReunionService reunionService) 
	{
		this.reunionService=reunionService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		TipoReunion p=reunionService.getTipoReunionById(Integer.parseInt(text));
        setValue(p);
	}

}

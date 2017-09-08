package agilemeetings.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import agilemeetings.model.Sprint;
import agilemeetings.service.SprintService;

public class SprintEditor extends PropertyEditorSupport {

	private final SprintService sprintService;
	public SprintEditor(SprintService sprintService) 
	{
		this.sprintService=sprintService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		Sprint p=sprintService.getSprintById(Integer.parseInt(text));
        setValue(p);
	}

}

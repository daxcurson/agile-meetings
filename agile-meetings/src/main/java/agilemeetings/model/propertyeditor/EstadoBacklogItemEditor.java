package agilemeetings.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import agilemeetings.model.EstadoBacklogItem;
import agilemeetings.service.ProductBacklogService;

public class EstadoBacklogItemEditor extends PropertyEditorSupport {
	private ProductBacklogService backlogService;

	public EstadoBacklogItemEditor(ProductBacklogService backlogService) 
	{
		this.backlogService=backlogService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		EstadoBacklogItem p=backlogService.getEstadoById(Integer.parseInt(text));
        setValue(p);
	}
}

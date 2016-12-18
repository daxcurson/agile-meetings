package agilemeetings.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import agilemeetings.model.ProductBacklogItem;
import agilemeetings.service.ProductBacklogService;

public class ProductBacklogItemEditor extends PropertyEditorSupport {

	private final ProductBacklogService productService;
	public ProductBacklogItemEditor(ProductBacklogService productService) 
	{
		this.productService=productService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		ProductBacklogItem p=productService.getBacklogItemById(Integer.parseInt(text));
        setValue(p);
	}
}

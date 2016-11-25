package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.ProductBacklogItem;

public interface ProductBacklogDAO 
{
	public ProductBacklogItem getItemById(int id);
	public List<ProductBacklogItem> getItems(int proyecto_id);
	public void save(ProductBacklogItem backlogItem);
	public void update(ProductBacklogItem backlogItem);
}

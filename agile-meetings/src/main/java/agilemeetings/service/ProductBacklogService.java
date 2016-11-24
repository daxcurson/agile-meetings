package agilemeetings.service;

import java.util.List;

import agilemeetings.model.ProductBacklogItem;
import agilemeetings.model.Proyecto;

public interface ProductBacklogService 
{
	public List<ProductBacklogItem> listarProductBacklog(Proyecto p);
}

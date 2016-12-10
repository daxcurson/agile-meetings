package agilemeetings.service;

import java.util.List;

import agilemeetings.model.EstadoBacklogItem;
import agilemeetings.model.ProductBacklogItem;
import agilemeetings.model.Proyecto;

public interface ProductBacklogService 
{
	public List<ProductBacklogItem> listarProductBacklog(Proyecto p);
	public List<EstadoBacklogItem> getEstados();
	public EstadoBacklogItem getEstadoById(int id);
	public void agregar(ProductBacklogItem backlogItem, Integer proyectoId);
	public ProductBacklogItem getBacklogItemById(Integer backlogId);
	public void grabarBacklogItem(ProductBacklogItem backlogItem);
	public void borrarBacklogItem(ProductBacklogItem backlogItem);
}

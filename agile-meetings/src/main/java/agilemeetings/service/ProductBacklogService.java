package agilemeetings.service;

import java.util.List;

import agilemeetings.model.EstadoBacklogItem;
import agilemeetings.model.ProductBacklogItem;
import agilemeetings.model.Proyecto;

public interface ProductBacklogService 
{
	public List<ProductBacklogItem> listarProductBacklog(Proyecto p);
	public List<EstadoBacklogItem> getEstados();
	public void agregar(ProductBacklogItem backlogItem, Integer proyectoId);
}

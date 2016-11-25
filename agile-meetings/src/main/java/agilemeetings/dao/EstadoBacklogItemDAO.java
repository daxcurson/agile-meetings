package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.EstadoBacklogItem;

public interface EstadoBacklogItemDAO 
{
	public EstadoBacklogItem getEstadoById(int id);
	public List<EstadoBacklogItem> getEstados();
}

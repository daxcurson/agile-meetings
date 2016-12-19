package agilemeetings.service;

import java.util.List;

import agilemeetings.exceptions.SprintAsociadaException;
import agilemeetings.model.Proyecto;
import agilemeetings.model.Sprint;
import agilemeetings.model.ProductBacklogItem;

public interface SprintService 
{
	public List<Sprint> listarSprints(Proyecto p);
	public void agregar(Sprint sprint,Proyecto p);
	public Sprint getSprintById(Integer sprintId);
	public void grabar(Sprint sprint);
	public void borrarSprint(Sprint sprint) throws SprintAsociadaException;
	public void asignarProductBacklogItems(Sprint sprint,List<ProductBacklogItem> listaItems);
}

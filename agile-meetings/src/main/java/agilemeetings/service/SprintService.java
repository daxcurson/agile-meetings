package agilemeetings.service;

import java.util.List;

import agilemeetings.model.Proyecto;
import agilemeetings.model.Sprint;

public interface SprintService 
{
	public List<Sprint> listarSprints(Proyecto p);
	public void agregar(Sprint sprint,Proyecto p);
	public Sprint getSprintById(Integer sprintId);
	public void grabar(Sprint sprint);
}

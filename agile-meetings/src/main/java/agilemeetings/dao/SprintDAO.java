package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.Sprint;

public interface SprintDAO 
{
	public List<Sprint> listarSprints(int id);
	public Sprint getById(Integer sprintId);
	public void grabar(Sprint s);
	public void agregar(Sprint s);

}

package agilemeetings.service;

import java.util.List;

import agilemeetings.exceptions.GrupoExistenteException;
import agilemeetings.model.Group;

public interface GroupService 
{
	public Group getById(long id);
	public List<Group> listGroups();
	public void create(Group group);
	public void save(Group group) throws GrupoExistenteException;
}

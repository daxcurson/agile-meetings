package agilemeetings.service;

import agilemeetings.model.Group;

public interface GroupService 
{
	public Group getById(long id);
	public void save(Group group);
}

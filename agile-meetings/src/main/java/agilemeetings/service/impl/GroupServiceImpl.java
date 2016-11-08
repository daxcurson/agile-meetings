package agilemeetings.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.GroupDAO;
import agilemeetings.model.Group;
import agilemeetings.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService
{
	@Autowired
	private GroupDAO groupDAO;
	@Override
	public Group getById(long id) 
	{
		return groupDAO.findGroupById(id);
	}
	@Override
	public void save(Group group) 
	{
		groupDAO.save(group);
	}
}

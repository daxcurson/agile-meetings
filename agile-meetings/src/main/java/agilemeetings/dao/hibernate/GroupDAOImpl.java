package agilemeetings.dao.hibernate;

import org.springframework.stereotype.Repository;

import agilemeetings.dao.GroupDAO;
import agilemeetings.model.Group;

@Repository
public class GroupDAOImpl extends GenericDAOImpl<Group> implements GroupDAO
{
	private String selectAll="from Group";
	private String selectOne="from Group where id=";
	
	@Override
	public String selectOne() 
	{
		return this.selectOne;
	}

	@Override
	public String selectAll() 
	{
		return this.selectAll;
	}
}

package agilemeetings.dao;

import java.util.List;
import agilemeetings.model.*;

public interface GroupDAO 
{
	List<Group> listAllGroups();
	Group findGroupById(long id);
	void save(Group g);
}

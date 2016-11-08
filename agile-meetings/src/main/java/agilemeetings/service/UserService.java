package agilemeetings.service;

import agilemeetings.model.User;

public interface UserService 
{
	public User getById(long id);
	public void save(User user);
}

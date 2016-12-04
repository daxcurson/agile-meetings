package agilemeetings.dao;

import agilemeetings.model.User;

public interface UserDAO extends GenericDAO<User>
{
	public User findByLogin(String login);
	public User findByEmail(String email);
	public User findByLoginOpenId(String loginOpenId);
	public User findByFacebookId(Long facebookId);
}
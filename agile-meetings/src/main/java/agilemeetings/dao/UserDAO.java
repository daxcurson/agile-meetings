package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.User;

public interface UserDAO 
{
	public User findByLogin(String login);
	public void save(User user);
	public User findById(Long userId);
	public User findByEmail(String email);
	public User findByLoginOpenId(String loginOpenId);
	public User findByFacebookId(Long facebookId);
	public List<User> listUsers();
}
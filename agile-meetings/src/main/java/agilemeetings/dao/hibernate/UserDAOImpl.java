package agilemeetings.dao.hibernate;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import agilemeetings.dao.UserDAO;
import agilemeetings.model.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO
{
	private static Logger log=LogManager.getLogger(UserDAOImpl.class);

	@Override
	public User findByLogin(String login) 
	{
		log.trace("Estoy en UserDAOImpl.findByLogin, login pedido:"+login);
		return (User) sessionFactory.getCurrentSession().createQuery("from User where username='"+login+"'").getSingleResult();
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByLoginOpenId(String loginOpenId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByFacebookId(Long facebookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String selectOne() 
	{
		return "from User where id=";
	}

	@Override
	public String selectAll() 
	{
		return "from User";
	}

}

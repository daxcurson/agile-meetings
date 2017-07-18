package agilemeetings.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.JuegoDAO;
import agilemeetings.model.Juego;

@Repository
public class JuegoDAOImpl implements JuegoDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void add(Juego juego) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(juego);
	}
}

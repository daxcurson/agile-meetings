package agilemeetings.dao.hibernate;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Juego> listarJuegosReunion(Integer reunionId) 
	{
		return sessionFactory.getCurrentSession().createQuery("from Juego where reunion_id="+reunionId).getResultList();
	}
}

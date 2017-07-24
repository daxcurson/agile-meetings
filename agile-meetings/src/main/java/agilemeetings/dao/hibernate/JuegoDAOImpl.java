package agilemeetings.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.JuegoDAO;
import agilemeetings.model.Juego;

@Repository
public class JuegoDAOImpl implements JuegoDAO
{
	private static Logger log=LogManager.getLogger(JuegoDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void add(Juego juego) 
	{
		log.trace("Agregando Juego");
		sessionFactory.getCurrentSession().saveOrUpdate(juego);
		log.trace("Juego agregado");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Juego> listarJuegosReunion(Integer reunionId) 
	{
		return sessionFactory.getCurrentSession().createQuery("from Juego where reunion_id="+reunionId).getResultList();
	}

	@Override
	public Juego getById(int juegoId) 
	{
		return (Juego) sessionFactory.getCurrentSession().createQuery("from Juego where id="+juegoId).getSingleResult();
	}
}

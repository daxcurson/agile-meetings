package agilemeetings.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agilemeetings.dao.TipoJuegoDAO;
import agilemeetings.model.TipoJuego;

@Repository
public class TipoJuegoDAOImpl implements TipoJuegoDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public TipoJuego getById(int parseInt) 
	{
		return (TipoJuego) sessionFactory.getCurrentSession().createQuery("from TipoJuego where id="+parseInt).getSingleResult();
	}
}

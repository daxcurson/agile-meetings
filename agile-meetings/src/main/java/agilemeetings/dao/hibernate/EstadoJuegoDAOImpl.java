package agilemeetings.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agilemeetings.dao.EstadoJuegoDAO;
import agilemeetings.model.EstadoJuego;

@Repository
public class EstadoJuegoDAOImpl implements EstadoJuegoDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public EstadoJuego getById(int id) 
	{
		return (EstadoJuego) sessionFactory.getCurrentSession().createQuery("from EstadoJuego where id="+id).getSingleResult();
	}

}

package agilemeetings.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.TarjetaDAO;
import agilemeetings.model.Tarjeta;

@Repository
public class TarjetaDAOImpl implements TarjetaDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Tarjeta getById(int id) 
	{
		return (Tarjeta) sessionFactory.getCurrentSession().createQuery("from Tarjeta where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tarjeta> getTarjetasJuego(int juegoId) 
	{
		return (List<Tarjeta>) sessionFactory.getCurrentSession().createQuery("from Tarjeta where juego_id="+juegoId).getResultList();
	}

	@Override
	@Transactional
	public void agregar(Tarjeta t) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public void grabar(Tarjeta t) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}
}

package agilemeetings.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agilemeetings.dao.EstadoBacklogItemDAO;
import agilemeetings.model.EstadoBacklogItem;

@Repository
public class EstadoBacklogItemDAOImpl implements EstadoBacklogItemDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public EstadoBacklogItem getEstadoById(int id) 
	{
		return (EstadoBacklogItem) sessionFactory.getCurrentSession().createQuery("from EstadoBacklogItem where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoBacklogItem> getEstados() 
	{
		return sessionFactory.getCurrentSession().createQuery("from EstadoBacklogItem").getResultList();
	}
}

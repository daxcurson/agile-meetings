package agilemeetings.dao.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.SprintDAO;
import agilemeetings.model.Sprint;

@Repository
public class SprintDAOImpl implements SprintDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Sprint> listarSprints(int id) 
	{
		return (List<Sprint>) sessionFactory.getCurrentSession().createQuery("from Sprint").getResultList();
	}

	@Override
	public Sprint getById(Integer sprintId) 
	{
		Sprint b=(Sprint) sessionFactory.getCurrentSession().createQuery("select s from Sprint s join fetch s.items where s.id="+sprintId).getSingleResult();
		Hibernate.initialize(b.getProyecto());
		return b;
	}
	@Override
	@Transactional
	public void agregar(Sprint s)
	{
		sessionFactory.getCurrentSession().save(s);
	}
	@Override
	@Transactional
	public void grabar(Sprint s)
	{
		sessionFactory.getCurrentSession().merge(s);
	}

	@Override
	@Transactional
	public void borrar(Sprint s) 
	{
		sessionFactory.getCurrentSession().delete(s);
	}
}

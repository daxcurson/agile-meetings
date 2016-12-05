package agilemeetings.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.GenericDAO;

public abstract class GenericDAOImpl<T> implements GenericDAO<T>
{
	@Autowired
	protected SessionFactory sessionFactory;

	public abstract String selectOne();
	public abstract String selectAll();
	
	@SuppressWarnings("unchecked")
	public List<T> listAll()
	{
		return sessionFactory.getCurrentSession().createQuery(this.selectAll()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public T findById(long id)
	{
		return (T)sessionFactory.getCurrentSession().createQuery(this.selectOne()+id).getSingleResult();
	}
	
	@Transactional
	public void save(T object)
	{
		sessionFactory.getCurrentSession().merge(object);
	}
	
	@Transactional
	public void create(T object)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(object);		
	}
}

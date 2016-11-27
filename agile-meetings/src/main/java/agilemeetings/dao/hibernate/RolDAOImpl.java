package agilemeetings.dao.hibernate;

import java.util.List;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.RolDAO;
import agilemeetings.model.Rol;

@Repository
public class RolDAOImpl implements RolDAO 
{
	//private static Logger log=LogManager.getLogger(RolDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Rol> listarRoles() 
	{
		return (List<Rol>) sessionFactory.getCurrentSession().createQuery("from Rol").getResultList();
	}

	@Override
	public Rol getRolById(int id) 
	{
		return (Rol) sessionFactory.getCurrentSession().createQuery("from Rol where id="+id).getSingleResult();
	}

	@Override
	@Transactional
	public void grabar(Rol rol) 
	{
		sessionFactory.getCurrentSession().merge(rol);
	}

	@Override
	public void agregar(Rol rol) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(rol);
	}
}

package agilemeetings.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.ProyectoDAO;
import agilemeetings.model.Proyecto;

@Repository
public class ProyectoDAOImpl implements ProyectoDAO
{
	static Logger log = Logger.getLogger(ProyectoDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Proyecto getProyectoById(int id) 
	{
		log.trace("Estoy en ProyectoDAOImpl.getProyectoById, id pedido:"+id);
		return (Proyecto) sessionFactory.getCurrentSession().createQuery("from Proyecto where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proyecto> listarProyectos() 
	{
		log.trace("Estoy en ProyectoDAOImpl.listarProyectos");
		return (List<Proyecto>) sessionFactory.getCurrentSession().createQuery("from Proyecto").getResultList();
	}

	@Override
	@Transactional
	public void agregar(Proyecto proyecto) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(proyecto);
	}

	@Override
	public void grabar(Proyecto proyecto) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(proyecto);
	}
}

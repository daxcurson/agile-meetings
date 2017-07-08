package agilemeetings.dao.hibernate;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.ReunionDAO;
import agilemeetings.model.Reunion;

@Repository
public class ReunionDAOImpl implements ReunionDAO
{
	private static Logger log=LogManager.getLogger(ReunionDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Reunion getReunionById(int id)
	{
		log.trace("Estoy en ReunionDAOImpl.getReunionById:"+id);
		return (Reunion) sessionFactory.getCurrentSession().createQuery("from Reunion where id="+id).getSingleResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Reunion> listarReuniones() 
	{
		return (List<Reunion>) sessionFactory.getCurrentSession().createQuery("from Reunion").getResultList();
	}
	@Override
	@Transactional
	public void agregar(Reunion reunion) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(reunion);
	}
	@Override
	@Transactional
	public void grabar(Reunion reunion) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(reunion);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Reunion> listarReunionesProyecto(int proyectoId) 
	{
		return (List<Reunion>) sessionFactory.getCurrentSession().createQuery("from Reunion where proyecto_id="+proyectoId).getResultList();
	}
	@Override
	public void borrar(int id) 
	{
		sessionFactory.getCurrentSession().delete(id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Reunion> listarReunionesParticipadasPersona(int personaId) 
	{
		return (List<Reunion>) sessionFactory.getCurrentSession().createQuery("select r from Reunion r,PersonaReunion rp where r.id=rp.reunion.id and rp.persona.id="+personaId).getResultList();
	}
}

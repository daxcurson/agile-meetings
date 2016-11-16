package agilemeetings.dao.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agilemeetings.dao.EstadoProyectoDAO;
import agilemeetings.model.EstadoProyecto;

@Repository
public class EstadoProyectoDAOImpl implements EstadoProyectoDAO
{
	static Logger log = Logger.getLogger(EstadoProyectoDAO.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public EstadoProyecto getEstadoById(int id) 
	{
		return (EstadoProyecto)sessionFactory.getCurrentSession().createQuery("from EstadoProyecto where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoProyecto> listarEstados() 
	{
		return sessionFactory.getCurrentSession().createQuery("from EstadoProyecto").getResultList();
	}

}

package agilemeetings.dao.hibernate;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agilemeetings.dao.EstadoProyectoDAO;
import agilemeetings.model.EstadoProyecto;

@Repository
public class EstadoProyectoDAOImpl implements EstadoProyectoDAO
{
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

package agilemeetings.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agilemeetings.dao.TipoReunionDAO;
import agilemeetings.model.TipoReunion;

@Repository
public class TipoReunionDAOImpl implements TipoReunionDAO 
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public TipoReunion getById(int id) 
	{
		return (TipoReunion)sessionFactory.getCurrentSession().createQuery("from TipoReunion where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoReunion> listarTiposReunion() 
	{
		return (List<TipoReunion>)sessionFactory.getCurrentSession().createQuery("from TipoReunion").getResultList();
	}

}

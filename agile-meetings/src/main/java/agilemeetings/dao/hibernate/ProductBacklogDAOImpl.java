package agilemeetings.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import agilemeetings.dao.ProductBacklogDAO;
import agilemeetings.model.ProductBacklogItem;

@Repository
public class ProductBacklogDAOImpl implements ProductBacklogDAO
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ProductBacklogItem getItemById(int id) 
	{
		return (ProductBacklogItem) sessionFactory.getCurrentSession().createQuery("from ProductBacklogItem where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductBacklogItem> getItems(int proyecto_id) 
	{
		return (List<ProductBacklogItem>) sessionFactory.getCurrentSession().createQuery("from ProductBacklogItem").getResultList();
	}

	@Override
	public void save(ProductBacklogItem backlogItem) 
	{
		sessionFactory.getCurrentSession().saveOrUpdate(backlogItem);
	}

}

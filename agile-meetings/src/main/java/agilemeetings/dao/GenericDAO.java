package agilemeetings.dao;

import java.util.List;

public interface GenericDAO<T> 
{
	public List<T> listAll();
	public T findById(long id);
	public void create(T object);
	public void save(T object);
}

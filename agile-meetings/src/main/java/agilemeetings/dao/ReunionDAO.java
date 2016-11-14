package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.Reunion;

public interface ReunionDAO 
{
	public List<Reunion> listarReuniones();
	public Reunion getReunionById(int id);
	public void save(Reunion reunion);
}

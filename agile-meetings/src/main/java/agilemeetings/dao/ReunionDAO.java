package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.Reunion;

public interface ReunionDAO 
{
	public List<Reunion> listarReuniones();
	public Reunion getReunionById(int id);
	public void agregar(Reunion reunion);
	public void grabar(Reunion reunion);
	public List<Reunion> listarReunionesProyecto(int proyectoId);
	public void borrar(int id);
}

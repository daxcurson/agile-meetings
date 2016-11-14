package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.Proyecto;

public interface ProyectoDAO 
{
	public Proyecto getProyectoById(int id);
	public List<Proyecto> listarProyectos();
	public void agregar(Proyecto proyecto);
	public void grabar(Proyecto proyecto);
}

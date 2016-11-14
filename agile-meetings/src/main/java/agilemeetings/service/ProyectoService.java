package agilemeetings.service;

import java.util.List;

import agilemeetings.exceptions.ProyectoExistenteException;
import agilemeetings.model.Proyecto;

public interface ProyectoService 
{
	public Proyecto getProyectoById(int id);
	public List<Proyecto> listarProyectos();
	public void agregar(Proyecto proyecto) throws ProyectoExistenteException;
	public void grabar(Proyecto proyecto) throws ProyectoExistenteException;
}

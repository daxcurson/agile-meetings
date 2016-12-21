package agilemeetings.service;

import java.util.List;

import agilemeetings.exceptions.ReunionExistenteException;
import agilemeetings.model.Reunion;

public interface ReunionService 
{
	public Reunion getReunionById(Integer reunionId);
	public List<Reunion> listarReuniones();
	public void agregar(Reunion reunion) throws ReunionExistenteException;
	public void grabar(Reunion reunion) throws ReunionExistenteException;
	public List<Reunion> listarReunionesProyecto(int proyectoId);
}

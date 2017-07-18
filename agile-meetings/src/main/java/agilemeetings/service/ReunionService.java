package agilemeetings.service;

import java.util.List;

import agilemeetings.exceptions.ReunionExistenteException;
import agilemeetings.model.Juego;
import agilemeetings.model.Reunion;
import agilemeetings.model.TipoReunion;

public interface ReunionService 
{
	public Reunion getReunionById(Integer reunionId);
	public List<Reunion> listarReuniones();
	public void agregar(Reunion reunion) throws ReunionExistenteException;
	public void grabar(Reunion reunion) throws ReunionExistenteException;
	public List<Reunion> listarReunionesProyecto(int proyectoId);
	public List<Reunion> listarReunionesParticipadasPersona(int personaId);
	public List<TipoReunion> listarTiposReunion();
	public TipoReunion getTipoReunionById(int id);
	public void borrar(int reunionId);
	public void grabarJuego(Reunion reunion,Juego juego) throws ReunionExistenteException;
}

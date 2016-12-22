package agilemeetings.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.ReunionDAO;
import agilemeetings.dao.TipoReunionDAO;
import agilemeetings.exceptions.ReunionExistenteException;
import agilemeetings.model.PersonaReunion;
import agilemeetings.model.Reunion;
import agilemeetings.model.TipoReunion;
import agilemeetings.service.ReunionService;

@Service
public class ReunionServiceImpl implements ReunionService
{
	private static Logger log=LogManager.getLogger(ReunionServiceImpl.class);
	@Autowired
	private ReunionDAO reunionDAO;
	@Autowired
	private TipoReunionDAO tipoReunionDAO;
	@Override
	public List<Reunion> listarReuniones() 
	{
		return reunionDAO.listarReuniones();
	}
	@Override
	public List<Reunion> listarReunionesProyecto(int proyectoId)
	{
		return reunionDAO.listarReunionesProyecto(proyectoId);
	}
	@Override
	public Reunion getReunionById(Integer reunionId) 
	{
		return reunionDAO.getReunionById(reunionId);
	}
	@Override
	public void agregar(Reunion reunion) throws ReunionExistenteException 
	{
		// Antes de grabar la reunion, me tengo que asegurar que tenga cada PersonaReunion
		// la reunion asignada!
		Iterator<PersonaReunion> p=reunion.getParticipantes().iterator();
		log.trace("Todos los participantes tienen en que reunion participaron???");
		while(p.hasNext())
		{
			PersonaReunion i=p.next();
			log.trace("Estuvo la persona "+i.getPersona().getNombre()+" en esta reunion?");
			if(i.getReunion()==null)
			{
				log.trace("Si, estuvo, hay que cargarle esta reunion");
				i.setReunion(reunion);
			}
		}
		// Listo, ahora si graba!
		reunionDAO.agregar(reunion);
	}
	@Override
	public void grabar(Reunion reunion) throws ReunionExistenteException 
	{
		// Antes de grabar la reunion, me tengo que asegurar que tenga cada PersonaReunion
		// la reunion asignada!
		Iterator<PersonaReunion> p=reunion.getParticipantes().iterator();
		log.fatal("Estoy en ReunionServiceImpl.grabar");
		log.trace("Todos los participantes tienen en que reunion participaron???");
		while(p.hasNext())
		{
			PersonaReunion i=p.next();
			log.trace("Estuvo la persona "+i.getPersona().getNombre()+" en esta reunion?");
			if(i.getReunion()==null)
			{
				log.trace("Si, estuvo, hay que cargarle esta reunion");
				i.setReunion(reunion);
			}
		}
		// Listo, ahora si graba!
		reunionDAO.grabar(reunion);
	}
	@Override
	public List<TipoReunion> listarTiposReunion() 
	{
		return tipoReunionDAO.listarTiposReunion();
	}
	@Override 
	public TipoReunion getTipoReunionById(int id)
	{
		return tipoReunionDAO.getById(id);
	}
}

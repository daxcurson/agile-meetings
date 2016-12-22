package agilemeetings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.ReunionDAO;
import agilemeetings.dao.TipoReunionDAO;
import agilemeetings.exceptions.ReunionExistenteException;
import agilemeetings.model.Reunion;
import agilemeetings.model.TipoReunion;
import agilemeetings.service.ReunionService;

@Service
public class ReunionServiceImpl implements ReunionService
{
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
		reunionDAO.agregar(reunion);
	}
	@Override
	public void grabar(Reunion reunion) throws ReunionExistenteException 
	{
		reunionDAO.grabar(reunion);
	}
	@Override
	public List<TipoReunion> listarTiposReunion() 
	{
		return tipoReunionDAO.listarTiposReunion();
	}
}

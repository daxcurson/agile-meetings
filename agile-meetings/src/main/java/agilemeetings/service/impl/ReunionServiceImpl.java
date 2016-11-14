package agilemeetings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.ReunionDAO;
import agilemeetings.exceptions.ReunionExistenteException;
import agilemeetings.model.Reunion;
import agilemeetings.service.ReunionService;

@Service
public class ReunionServiceImpl implements ReunionService
{
	@Autowired
	private ReunionDAO reunionDAO;
	@Override
	public List<Reunion> listarReuniones() 
	{
		return reunionDAO.listarReuniones();
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
	
}

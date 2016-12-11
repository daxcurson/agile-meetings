package agilemeetings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.SprintDAO;
import agilemeetings.exceptions.SprintAsociadaException;
import agilemeetings.model.Proyecto;
import agilemeetings.model.Sprint;
import agilemeetings.service.SprintService;

@Service
public class SprintServiceImpl implements SprintService
{
	@Autowired
	SprintDAO sprintDAO;
	@Override
	public List<Sprint> listarSprints(Proyecto p) 
	{
		return sprintDAO.listarSprints(p.getId());
	}

	@Override
	@Transactional
	public void agregar(Sprint sprint,Proyecto p) 
	{
		sprint.setProyecto(p);
		sprintDAO.agregar(sprint);
	}

	@Override
	public Sprint getSprintById(Integer sprintId) 
	{
		return sprintDAO.getById(sprintId);
	}

	@Override
	public void grabar(Sprint sprint) 
	{
		sprintDAO.grabar(sprint);
	}

	@Override
	public void borrarSprint(Sprint sprint) throws SprintAsociadaException 
	{
		// Hay que chequear que este sprint no tenga items de product backlog asociados
		// antes de borrar.
		if(sprint.getItems().size()>0)
			throw new SprintAsociadaException();
		else
			sprintDAO.borrar(sprint);
	}
}

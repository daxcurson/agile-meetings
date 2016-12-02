package agilemeetings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.SprintDAO;
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
}

package agilemeetings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.ReunionDAO;
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
		// TODO Auto-generated method stub
		return null;
	}
	
}

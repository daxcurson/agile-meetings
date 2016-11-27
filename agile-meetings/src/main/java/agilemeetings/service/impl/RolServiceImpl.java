package agilemeetings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.RolDAO;
import agilemeetings.model.Rol;
import agilemeetings.service.RolService;

@Service
public class RolServiceImpl implements RolService
{

	@Autowired
	private RolDAO rolDAO;
	@Override
	public Rol getRolById(int id) 
	{
		return rolDAO.getRolById(id);
	}

	@Override
	public List<Rol> listarRoles() 
	{
		return rolDAO.listarRoles();
	}

	@Override
	public void agregar(Rol rol) 
	{
		rolDAO.agregar(rol);
	}

	@Override
	public void grabar(Rol rol) 
	{
		rolDAO.grabar(rol);
	}
}

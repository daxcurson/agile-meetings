package agilemeetings.service;

import java.util.List;

import agilemeetings.model.Rol;

public interface RolService 
{
	public Rol getRolById(int id);
	public List<Rol> listarRoles();
	public void agregar(Rol rol);
	public void grabar(Rol rol);
}

package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.Rol;

public interface RolDAO 
{
	public List<Rol> listarRoles();
	public Rol getRolById(int id);
	public void grabar(Rol rol);
	public void agregar(Rol rol);
}

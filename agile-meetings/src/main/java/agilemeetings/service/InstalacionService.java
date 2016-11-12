package agilemeetings.service;

import agilemeetings.exceptions.UsuarioExistenteException;
import agilemeetings.model.User;

public interface InstalacionService 
{
	public void grabarUsuarioAdministrador(User user) throws UsuarioExistenteException;
}

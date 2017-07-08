package agilemeetings.service.impl;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.dao.PersonaDAO;
import agilemeetings.dao.UserDAO;
import agilemeetings.exceptions.PersonaExistenteException;
import agilemeetings.model.Persona;
import agilemeetings.model.User;
import agilemeetings.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService
{
	@Autowired
	private PersonaDAO personaDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public Persona getPersonaById(int id) 
	{
		return personaDAO.getById(id);
	}

	@Override
	public List<Persona> listarPersonas() 
	{
		return personaDAO.listarPersonas();
	}

	@Override
	public void agregarPersona(Persona p) throws PersonaExistenteException 
	{
		try
		{
			// Si es usuario del sistema, hay que grabar un usuario.
			User user=p.getUser();
			user.setEnabled(1);
	        BCryptPasswordEncoder pwe=new BCryptPasswordEncoder();
	        user.setPassword(pwe.encode(user.getPassword()));
	        user.setConfirm_password(user.getPassword());
			if(p.getUsuario_sistema())
				userDAO.save(user);
			personaDAO.agregar(p);
		}
        catch(ConstraintViolationException e)
        {
        	// Si se arroja esta excepcion, es porque el usuario ya existe.
        	// Convertirla en la excepcion UsuarioExistente
        	throw new PersonaExistenteException();
        }
	}

	@Override
	@Transactional
	public void grabarPersona(Persona p) throws PersonaExistenteException 
	{
		try
		{
			User user=p.getUser();
	        BCryptPasswordEncoder pwe=new BCryptPasswordEncoder();
	        user.setPassword(pwe.encode(user.getPassword()));
	        user.setConfirm_password(user.getPassword());
			personaDAO.grabar(p);
		}
        catch(ConstraintViolationException e)
        {
        	// Si se arroja esta excepcion, es porque el usuario ya existe.
        	// Convertirla en la excepcion UsuarioExistente
        	throw new PersonaExistenteException();
        }
	}

}

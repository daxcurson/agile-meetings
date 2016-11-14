package agilemeetings.service.impl;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.ProyectoDAO;
import agilemeetings.exceptions.ProyectoExistenteException;
import agilemeetings.model.Proyecto;
import agilemeetings.service.ProyectoService;

@Service
public class ProyectoServiceImpl implements ProyectoService
{
	@Autowired
	private ProyectoDAO proyectoDAO;

	@Override
	public List<Proyecto> listarProyectos() 
	{
		return proyectoDAO.listarProyectos();
	}

	@Override
	public void agregar(Proyecto proyecto) throws ProyectoExistenteException {
        try
        {
        	proyectoDAO.agregar(proyecto);
        }
        catch(ConstraintViolationException e)
        {
        	// Si se arroja esta excepcion, es porque el usuario ya existe.
        	// Convertirla en la excepcion UsuarioExistente
        	throw new ProyectoExistenteException();
        }
	}

	@Override
	public Proyecto getProyectoById(int id) 
	{
		return proyectoDAO.getProyectoById(id);
	}

	@Override
	public void grabar(Proyecto proyecto) throws ProyectoExistenteException 
	{
		proyectoDAO.grabar(proyecto);
	}
}

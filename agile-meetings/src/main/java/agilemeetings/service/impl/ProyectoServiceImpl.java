package agilemeetings.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.EstadoProyectoDAO;
import agilemeetings.dao.ProyectoDAO;
import agilemeetings.exceptions.ProyectoExistenteException;
import agilemeetings.model.EstadoProyecto;
import agilemeetings.model.Proyecto;
import agilemeetings.model.RolPersona;
import agilemeetings.service.ProyectoService;

@Service
public class ProyectoServiceImpl implements ProyectoService
{
	@Autowired
	private ProyectoDAO proyectoDAO;
	@Autowired
	private EstadoProyectoDAO estadoProyectoDAO;

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
		Proyecto p=proyectoDAO.getProyectoById(id);
		// Tengo que inicializar ahora la lista de miembros de este proyecto.
		List<RolPersona> miembros=p.getMiembros();
		Hibernate.initialize(miembros);
		return p;
	}

	@Override
	public void grabar(Proyecto proyecto) throws ProyectoExistenteException 
	{
		proyectoDAO.grabar(proyecto);
	}

	@Override
	public List<EstadoProyecto> listarEstadosProyecto() 
	{
		return estadoProyectoDAO.listarEstados();
	}

	@Override
	public EstadoProyecto getEstadoProyectoById(int parseLong) 
	{
		return estadoProyectoDAO.getEstadoById(parseLong);
	}
}

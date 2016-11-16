package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.EstadoProyecto;

public interface EstadoProyectoDAO 
{
	public EstadoProyecto getEstadoById(int id);
	public List<EstadoProyecto> listarEstados();
}

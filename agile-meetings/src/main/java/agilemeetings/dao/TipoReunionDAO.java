package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.TipoReunion;

public interface TipoReunionDAO 
{
	public TipoReunion getById(int id);
	public List<TipoReunion> listarTiposReunion();
}

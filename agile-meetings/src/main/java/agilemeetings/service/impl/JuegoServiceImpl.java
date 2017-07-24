package agilemeetings.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agilemeetings.dao.EstadoJuegoDAO;
import agilemeetings.dao.JuegoDAO;
import agilemeetings.dao.TipoJuegoDAO;
import agilemeetings.model.EstadoJuego;
import agilemeetings.model.Juego;
import agilemeetings.model.TipoJuego;
import agilemeetings.service.JuegoService;

@Service
public class JuegoServiceImpl implements JuegoService
{
	@Autowired
	private TipoJuegoDAO tipoJuegoDAO;
	@Autowired
	private JuegoDAO juegoDAO;
	@Autowired
	private EstadoJuegoDAO estadoJuegoDAO;
	
	@Override
	public TipoJuego getTipoJuegoById(int parseInt) 
	{
		return tipoJuegoDAO.getById(parseInt);
	}
	@Override
	public List<TipoJuego> listarTiposJuego()
	{
		return tipoJuegoDAO.listar();
	}
	@Override
	public void agregar(Juego juego, Integer reunionId) 
	{
		juegoDAO.add(juego);
	}
	@Override
	public List<Juego> listarJuegosReunion(Integer reunionId) 
	{
		return juegoDAO.listarJuegosReunion(reunionId);
	}
	@Override
	public EstadoJuego getEstadoJuegoById(int parseInt) 
	{
		return estadoJuegoDAO.getById(parseInt);
	}

}

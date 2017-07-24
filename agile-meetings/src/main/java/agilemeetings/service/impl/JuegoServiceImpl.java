package agilemeetings.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private static Logger log=LogManager.getLogger(JuegoServiceImpl.class);
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
	@Transactional
	public void agregar(Juego juego, Integer reunionId) 
	{
		log.trace("Agregando juego");
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
	@Override
	public List<EstadoJuego> listarEstadosJuego() 
	{
		return estadoJuegoDAO.listar();
	}
	@Override
	public Juego getJuegoById(int juegoId) 
	{
		return juegoDAO.getById(juegoId);
	}
}

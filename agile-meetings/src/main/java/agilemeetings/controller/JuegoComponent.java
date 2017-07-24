package agilemeetings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import agilemeetings.model.EstadoJuego;
import agilemeetings.model.TipoJuego;
import agilemeetings.service.JuegoService;

@Component
public class JuegoComponent 
{
	@Autowired
	private JuegoService juegoService;
	
	public List<TipoJuego> listarTiposJuego()
	{
		return juegoService.listarTiposJuego();
	}
	public List<EstadoJuego> listarEstadosJuego()
	{
		return juegoService.listarEstadosJuego();
	}
}

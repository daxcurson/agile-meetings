package agilemeetings.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import agilemeetings.model.EstadoJuego;
import agilemeetings.model.Juego;
import agilemeetings.model.MadSadGlad;
import agilemeetings.model.Reunion;
import agilemeetings.model.TipoJuego;
import agilemeetings.service.JuegoService;
import agilemeetings.service.ReunionService;

@Component
public class JuegoComponent 
{
	private static Logger log=LogManager.getLogger(JuegoComponent.class);
	@Autowired
	private ReunionService reunionService;
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
	/**
	 * Factory Method para crear juegos.
	 * @param tipoJuego
	 * @param estadoJuego
	 * @return
	 */
	@Transactional
	public Juego crearJuego(int tipoJuegoId,int estadoJuegoId,int reunionId)
	{
		TipoJuego tipoJuego=juegoService.getTipoJuegoById(tipoJuegoId);
		log.trace("De que tipo es el juego? -> "+tipoJuego.getCodigo());
		if(tipoJuego.getCodigo().equals("MAD"))
		{
			MadSadGlad j=new MadSadGlad();
			EstadoJuego estadoJuego=juegoService.getEstadoJuegoById(estadoJuegoId);
			Reunion r=reunionService.getReunionById(reunionId);
			j.setEstado_juego(estadoJuego);
			j.setTipo_juego(tipoJuego);
			j.setCodigo(tipoJuego.getCodigo());
			j.setReunion(r);
			// Ahora grabamos el juego.
			log.trace("Grabando juego");
			juegoService.agregar(j, reunionId);
			return j;
		}
		else
			return null;
	}
	public String mensajeExitoso()
	{
		return "Juego agregado";
	}
}

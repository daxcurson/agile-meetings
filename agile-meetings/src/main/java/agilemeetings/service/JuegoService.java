package agilemeetings.service;

import java.util.List;

import agilemeetings.model.EstadoJuego;
import agilemeetings.model.Juego;
import agilemeetings.model.MadSadGlad;
import agilemeetings.model.Tarjeta;
import agilemeetings.model.TipoJuego;

public interface JuegoService 
{
	TipoJuego getTipoJuegoById(int parseInt);
	void agregar(Juego juego, Integer reunionId);
	List<TipoJuego> listarTiposJuego();
	List<Juego> listarJuegosReunion(Integer reunionId);
	EstadoJuego getEstadoJuegoById(int parseInt);
	List<EstadoJuego> listarEstadosJuego();
	Juego getJuegoById(int juegoId);
	void agregarTarjeta(Tarjeta tarjeta,MadSadGlad juego);
}

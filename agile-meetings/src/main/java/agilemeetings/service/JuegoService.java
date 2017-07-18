package agilemeetings.service;

import agilemeetings.model.Juego;
import agilemeetings.model.TipoJuego;

public interface JuegoService 
{
	TipoJuego getTipoJuegoById(int parseInt);
	void agregar(Juego juego, Integer reunionId);
}

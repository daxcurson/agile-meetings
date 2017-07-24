package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.TipoJuego;

public interface TipoJuegoDAO 
{
	TipoJuego getById(int parseInt);
	List<TipoJuego> listar();
}

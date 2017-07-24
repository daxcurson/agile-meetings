package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.Juego;

public interface JuegoDAO 
{
	void add(Juego juego);
	List<Juego> listarJuegosReunion(Integer reunionId);
	Juego getById(int juegoId);
}

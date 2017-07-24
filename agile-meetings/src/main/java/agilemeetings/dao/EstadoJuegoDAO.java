package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.EstadoJuego;

public interface EstadoJuegoDAO 
{
	EstadoJuego getById(int id);
	List<EstadoJuego> listar();
}

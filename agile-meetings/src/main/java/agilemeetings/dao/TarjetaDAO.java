package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.Tarjeta;

public interface TarjetaDAO 
{
	Tarjeta getById(int id);
	List<Tarjeta> getTarjetasJuego(int juegoId);
	void agregar(Tarjeta t);
	void grabar(Tarjeta t);
}

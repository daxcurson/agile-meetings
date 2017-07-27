package agilemeetings.dao;

import java.util.List;

import agilemeetings.model.Tarjeta;

public interface TarjetaDAO 
{
	Tarjeta getById(int id);
	List<Tarjeta> getTarjetasJuego(int juegoId);
	void agregar(Tarjeta t);
	void grabar(Tarjeta t);
	List<Tarjeta> getTarjetasMad(int juegoId);
	List<Tarjeta> getTarjetasSad(int juegoId);
	List<Tarjeta> getTarjetasGlad(int juegoId);
}

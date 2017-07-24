package agilemeetings.model;

import java.io.Serializable;

public class JuegoData implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5763280109175805591L;
	private int tipo_juego;
	private int estado_juego;
	public int getTipo_juego() {
		return tipo_juego;
	}
	public void setTipo_juego(int tipo_juego_id) {
		this.tipo_juego = tipo_juego_id;
	}
	public int getEstado_juego() {
		return estado_juego;
	}
	public void setEstado_juego(int estado_juego_id) {
		this.estado_juego = estado_juego_id;
	}
}

package agilemeetings.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="juegos")
public class Juego 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="reunion_id")
	private Reunion reunion;
	@ManyToOne
	@JoinColumn(name="tipo_juego_id")
	private TipoJuego tipo_juego;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@JsonBackReference
	public Reunion getReunion() {
		return reunion;
	}
	public void setReunion(Reunion reunion) {
		this.reunion = reunion;
	}
	public TipoJuego getTipo_juego() {
		return tipo_juego;
	}
	public void setTipo_juego(TipoJuego tipo_juego) {
		this.tipo_juego = tipo_juego;
	}
}

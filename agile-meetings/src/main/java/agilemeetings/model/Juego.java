package agilemeetings.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="juegos")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="codigo",discriminatorType=DiscriminatorType.STRING)
public class Juego 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@ManyToOne
	@JoinColumn(name="reunion_id")
	protected Reunion reunion;
	@ManyToOne
	@JoinColumn(name="tipo_juego_id")
	protected TipoJuego tipo_juego;
	@Column(name="codigo",insertable=false, updatable=false)
	protected String codigo;
	@ManyToOne
	@JoinColumn(name="estado_juego_id")
	protected EstadoJuego estado_juego;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public EstadoJuego getEstado_juego() {
		return estado_juego;
	}
	public void setEstado_juego(EstadoJuego estado_juego) {
		this.estado_juego = estado_juego;
	}
}

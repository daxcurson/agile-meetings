package agilemeetings.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="reuniones")
public class Reunion 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private Date fecha_comienzo;
	private Date fecha_fin;
	@OneToMany(targetEntity=PersonaReunion.class,cascade={CascadeType.ALL},
	mappedBy="reunion")
	private List<PersonaReunion> participantes;
	@ManyToOne
	@JoinColumn(name="proyecto_id")
	private Proyecto proyecto;
	@OneToMany(targetEntity=Juego.class,cascade={CascadeType.ALL},
	mappedBy="reunion",fetch=FetchType.LAZY)
	private List<Juego> juegos;
	@ManyToOne
	@JoinColumn(name="tipo_reunion_id")
	private TipoReunion tipo_reunion;
	@NotEmpty
	private String asunto;
	private String resumen;
	private String acciones;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha_comienzo() {
		return fecha_comienzo;
	}
	public void setFecha_comienzo(Date fecha_comienzo) {
		this.fecha_comienzo = fecha_comienzo;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public List<PersonaReunion> getParticipantes() {
		return participantes;
	}
	public void setParticipantes(List<PersonaReunion> participantes) {
		this.participantes = participantes;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public List<Juego> getJuegos() {
		return juegos;
	}
	public void setJuegos(List<Juego> juegos) {
		this.juegos = juegos;
	}
	public TipoReunion getTipo_reunion() {
		return tipo_reunion;
	}
	public void setTipo_reunion(TipoReunion tipo_reunion) {
		this.tipo_reunion = tipo_reunion;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getAcciones() {
		return acciones;
	}
	public void setAcciones(String acciones) {
		this.acciones = acciones;
	}
}

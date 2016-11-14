package agilemeetings.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="proyectos")
public class Proyecto 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date fecha_creacion;
	private Date fecha_comienzo;
	private Date fecha_fin;
	@ManyToOne
	@JoinColumn(name="estado_id")
	private EstadoProyecto estado;
	private String nombre;
	@OneToMany(targetEntity=RolUsuario.class,cascade={CascadeType.ALL},
	mappedBy="proyecto",fetch=FetchType.LAZY)
	private List<RolUsuario> miembros;
	@OneToMany(targetEntity=Reunion.class,cascade={CascadeType.ALL},
	mappedBy="proyecto",fetch=FetchType.LAZY)
	private List<Reunion> reuniones;
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
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
	public EstadoProyecto getEstado() {
		return estado;
	}
	public void setEstado(EstadoProyecto estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<RolUsuario> getMiembros() {
		return miembros;
	}
	public void setMiembros(List<RolUsuario> miembros) {
		this.miembros = miembros;
	}
	public List<Reunion> getReuniones() {
		return reuniones;
	}
	public void setReuniones(List<Reunion> reuniones) {
		this.reuniones = reuniones;
	}
}

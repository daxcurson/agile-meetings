package agilemeetings.model;

import java.util.Date;
import java.util.LinkedList;
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

import org.hibernate.annotations.Cascade;

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
	@OneToMany(targetEntity=RolPersona.class,
	mappedBy="proyecto",fetch=FetchType.LAZY,orphanRemoval=true)
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	private List<RolPersona> miembros=new LinkedList<RolPersona>();
	@OneToMany(targetEntity=Reunion.class,cascade={CascadeType.ALL},
	mappedBy="proyecto",fetch=FetchType.LAZY)
	private List<Reunion> reuniones=new LinkedList<Reunion>();
	@OneToMany(targetEntity=Sprint.class,cascade={CascadeType.ALL},
	mappedBy="proyecto",fetch=FetchType.LAZY)
	private List<Sprint> sprints;
	@OneToMany(targetEntity=ProductBacklogItem.class,mappedBy="proyecto",
	fetch=FetchType.LAZY)
	private List<ProductBacklogItem> productBacklog;
	
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
	public List<RolPersona> getMiembros() {
		return miembros;
	}
	public void setMiembros(List<RolPersona> miembros) {
		this.miembros = miembros;
	}
	public List<Reunion> getReuniones() {
		return reuniones;
	}
	public void setReuniones(List<Reunion> reuniones) {
		this.reuniones = reuniones;
	}
	public List<Sprint> getSprints() {
		return sprints;
	}
	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}
	public List<ProductBacklogItem> getProductBacklog() {
		return productBacklog;
	}
	public void setProductBacklog(List<ProductBacklogItem> productBacklog) {
		this.productBacklog = productBacklog;
	}
	@Override
	public boolean equals(Object otro)
	{
		if(otro instanceof Proyecto)
		{
			Proyecto o=(Proyecto)otro;
			if(
				(o.getEstado()==null && this.getEstado()==null) || (o.getEstado()!=null && this.getEstado()!=null && o.getEstado().equals(this.getEstado()))
				&&
				(o.getFecha_comienzo()==null && this.getFecha_comienzo()==null) || (o.getFecha_comienzo()!=null && this.getFecha_comienzo()!=null && o.getFecha_comienzo().equals(this.getFecha_comienzo()))
				&&
				(o.getFecha_fin()==null && this.getFecha_fin()==null) || (o.getFecha_fin()!=null && this.getFecha_fin()!=null && o.getFecha_fin().equals(this.getFecha_fin()))
				&&
				(o.getFecha_creacion()==null && this.getFecha_creacion()==null) || (o.getFecha_creacion()!=null && this.getFecha_creacion()!=null && o.getFecha_creacion().equals(this.getFecha_creacion()))
				&&
				(o.getNombre()==null && this.getNombre()==null) || (o.getNombre()!=null && this.getNombre()!=null && o.getNombre().equals(this.getNombre()))
			)
			{
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
}

package agilemeetings.model;

import java.util.Date;
import java.util.List;

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
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="sprints")
public class Sprint 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date fecha_comienzo;
	private Date fecha_fin;
	
	@ManyToOne
	@JoinColumn(name="proyecto_id")
	private Proyecto proyecto;
	@ManyToOne
	@JoinColumn(name="estado_id")
	private EstadoProyecto estado;
	@OneToMany(targetEntity=ItemSprint.class,mappedBy="sprint",fetch=FetchType.LAZY,orphanRemoval=true)
	@Cascade({CascadeType.ALL})
	private List<ItemSprint> items;
	@NotEmpty
	private String nombre;
	@NotEmpty
	private String descripcion;
	
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
	public List<ItemSprint> getItems() {
		return items;
	}
	public void setItems(List<ItemSprint> items) {
		this.items = items;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public EstadoProyecto getEstado() {
		return estado;
	}
	public void setEstado(EstadoProyecto estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public boolean equals(Object otro)
	{
		// Una sprint va a ser igual a otra si tiene sus valores iguales.
		Sprint o=(Sprint) otro;
		if(
			o.getDescripcion().equals(this.getDescripcion())
			&&
			o.getEstado().equals(this.getEstado())
			&&
			o.getFecha_comienzo().equals(this.getFecha_comienzo())
			&&
			o.getFecha_fin().equals(this.getFecha_fin())
			&&
			o.getNombre().equals(this.getNombre())
			&&
			o.getProyecto().equals(this.getProyecto())
				)
		{
			return true;
		}
		else
			return false;
	}
}

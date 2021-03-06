package agilemeetings.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="product_backlog_items")
public class ProductBacklogItem 
{
	//private static Logger log=LogManager.getLogger(ProductBacklogItem.class);

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date fecha_creacion;
	@NotEmpty
	private String titulo;
	@Column(columnDefinition = "TEXT")
	private String descripcion;
	@ManyToOne
	@JoinColumn(name="proyecto_id")
	private Proyecto proyecto;
	@ManyToOne
	@JoinColumn(name="estado_id")
	private EstadoBacklogItem estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public EstadoBacklogItem getEstado() {
		return estado;
	}
	public void setEstado(EstadoBacklogItem estado) {
		this.estado = estado;
	}
	public boolean equals(Object otro)
	{
		if(otro instanceof ProductBacklogItem)
		{
			ProductBacklogItem o=(ProductBacklogItem)otro;
			if(
				o.getDescripcion().equals(this.getDescripcion())
				&&
				o.getTitulo().equals(this.getTitulo())
				&&
				o.getEstado().equals(this.getEstado())
				&&
				o.getFecha_creacion().equals(this.getFecha_creacion())
			)
				return true;
			else
				return false;
		}
		else
			return false;
	}
}

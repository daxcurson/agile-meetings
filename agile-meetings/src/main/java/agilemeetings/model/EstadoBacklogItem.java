package agilemeetings.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estados_backlog_item")
public class EstadoBacklogItem 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nombre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean equals(Object otro)
	{
		if(otro instanceof EstadoBacklogItem)
		{
			EstadoBacklogItem o=(EstadoBacklogItem)otro;
			if(
				o.getNombre().equals(this.getNombre())
			)
				return true;
			else
				return false;
		}
		else
			return false;
	}
}

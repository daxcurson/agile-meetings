package agilemeetings.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="items_sprint")
/**
 * Representa un item del backlog en un sprint. Cada item
 * puede tener p/ej. su propio estado dentro del sprint. Puede ser que el mismo
 * item haya sido tomado en varios sprints.
 * @author daxcurson
 *
 */
public class ItemSprint 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name="item_id")
	private ProductBacklogItem item;
	@ManyToOne
	@JoinColumn(name="sprint_id")
	private Sprint sprint;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProductBacklogItem getItem() {
		return item;
	}
	public void setItem(ProductBacklogItem item) {
		this.item = item;
	}
	public Sprint getSprint() {
		return sprint;
	}
	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	@Override
	public boolean equals(Object otro)
	{
		if(otro instanceof ItemSprint)
		{
			if(this.getItem().equals(((ItemSprint) otro).getItem()) && 
					this.getSprint().equals(((ItemSprint) otro).getSprint()))
				return true;
		}
		return false;
	}
}

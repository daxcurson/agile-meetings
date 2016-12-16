package agilemeetings.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SprintList implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6426924527065665208L;
	private List<ListItem> items;
	
	public SprintList()
	{
		if(getItems()==null)
			setItems(new LinkedList<ListItem>());
	}

	public List<ListItem> getItems() {
		return items;
	}

	public void setItems(List<ListItem> items) {
		this.items = items;
	}
}

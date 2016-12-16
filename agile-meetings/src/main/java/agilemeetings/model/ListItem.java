package agilemeetings.model;

import java.io.Serializable;

public class ListItem implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3541035918085046633L;
	private int id;
	private String value;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

package agilemeetings.model;

/**
 * Un item de un Listbox, usado para el componente pickList, pirateado de internet.
 * @author daxcurson
 *
 */
public class ListboxItem 
{
	private int id;
	private String text;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}

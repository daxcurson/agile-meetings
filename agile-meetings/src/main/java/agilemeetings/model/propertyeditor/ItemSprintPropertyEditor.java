package agilemeetings.model.propertyeditor;

import java.util.LinkedList;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import agilemeetings.model.ItemSprint;

public class ItemSprintPropertyEditor extends CustomCollectionEditor {

	public ItemSprintPropertyEditor(Class<LinkedList<ItemSprint>> collectionType) {
		super(collectionType);
	}
/*	protected Object convertElement(Object element) {
		if (element instanceof Staff) {
			System.out.println("Converting from Staff to Staff: " + element);
			return element;
		}
		if (element instanceof String) {
			Staff staff = staffCache.get(element);
			System.out.println("Looking up staff for id " + element + ": " + staff);
			return staff;
		}
		System.out.println("Don't know what to do with: " + element);
		return null;
	}*/
}

package fr.maxlego08.menu.api;

import java.util.Collection;
import java.util.List;

import fr.maxlego08.menu.api.buttons.Button;

public interface Inventory {

	/**
	 * Returns the size of the inventory
	 * 
	 * @return size
	 */
	public int size();

	/**
	 * Returns the name of the inventory
	 * 
	 * @return name
	 */
	public String getName();
	
	/**
	 * Returns the name of the file
	 * 
	 * @return fileName
	 */
	public String getFileName();
	
	/**
	 * Return the list of buttons
	 * 
	 * @return buttons
	 */
	public Collection<Button> getButtons();
	
	/**
	 * Returns the list of buttons according to a type
	 * 
	 * @param button type
	 * @return buttons list
	 */
	public <T extends Button> List<T> getButtons(Class<T> type);
	
}

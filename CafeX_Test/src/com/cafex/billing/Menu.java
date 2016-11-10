package com.cafex.billing;

import java.util.HashMap;

/**
 * A Menu object will hold a number of MenuItem objects
 * @author kieran.boparai
 *
 */
public class Menu {
	
	//Stored as HashMap to allow easy reference to individual items
	private HashMap<String, MenuItem> menu;
	
	/**
	 * Initialise the HashMap
	 */
	public Menu(){
		menu = new HashMap<String, MenuItem>();
		setupMenu();
	}
	
	/**
	 * Used to initialise the items into the Menu. This is private and can only be called on initialisation of a new Menu object.
	 * ** Story 6 ** Additional item "TestItem" has been added to test the rounding capability of the Service Charge calculations
	 * Also removed Cast to Float as it was uneccessary.
	 */
	private void setupMenu(){
		MenuItem toAdd = new MenuItem("Cola", MenuItem.MenuItems.COLD_DRINK, 0.50);
		menu.put("cola", toAdd);
		toAdd = new MenuItem("Coffee", MenuItem.MenuItems.HOT_DRINK, 1.00);
		menu.put("coffee", toAdd);
		toAdd = new MenuItem("Cheese Sandwich", MenuItem.MenuItems.COLD_FOOD, 2.00);
		menu.put("cheesesandwich", toAdd);
		toAdd = new MenuItem("Steak Sandwich", MenuItem.MenuItems.HOT_FOOD, 4.50);
		menu.put("steaksandwich", toAdd);
		toAdd = new MenuItem("Test Item", MenuItem.MenuItems.COLD_DRINK, 1.597);
		menu.put("testitem", toAdd);
	}
	
	/**
	 * Used to get the size of the menu
	 * @return - int - Size of the Menu HashMap
	 */
	public int getMenuSize(){
		return menu.size();
	}
	
	/**
	 * Used to get a single MenuItem from the Menu by name
	 * @param itemName - String - Item Name of the item you want
	 * @return - MenuItem - A full MenuItem object containing details of the item
	 */
	public MenuItem getItem(String itemName){
		// format item name by converting to lowercase (As per hashmap names) and remove any spaces. Will ensure consistency
		String formattedName = itemName.toLowerCase().replaceAll("\\ ", "");
		return menu.get(formattedName); // if the name doesn't match null will be returned
	}
}

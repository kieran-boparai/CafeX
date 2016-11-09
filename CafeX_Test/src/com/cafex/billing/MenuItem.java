package com.cafex.billing;

/**
 * A MenuItem object will represent a single item on an order
 * @author kieran.boparai
 *
 */
public class MenuItem {
	
	// list of possible item types
	public enum MenuItems{COLD_FOOD, COLD_DRINK, HOT_FOOD, HOT_DRINK};
	
	// For each item we will store the name f the product, what kind of product it is and the price
	private String itemName;
	private MenuItems itemType;
	private double itemPrice;
	
	/**
	 * Creates a single Menu Item
	 * @param itemName - String - Name of theproduct
	 * @param itemType - enum (MenuItems) - The type of the product (COLD_FOOD, COLD_DRINK, HOT_FOOD, HOT_DRINK)
	 * @param itemPrice - double - The cost of the pproduct
	 */
	public MenuItem(String itemName, MenuItems itemType, double itemPrice){
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemPrice = itemPrice;
	}
	
	/**
	 * Used to get the name of the item
	 * @return - String - Name of the item/product
	 */
	public String getItemName(){
		return itemName;
	}
	
	/**
	 * Used to get the type of the item
	 * @return - enum (MenuItems) - Can be COLD_FOOD, COLD_DRINK, HOT_FOOD, HOT_DRINK
	 */
	public MenuItems getItemType(){
		return itemType;
	}
	
	/**
	 * Used to get the price of the item
	 * @return - double - Price of the item
	 */
	public double getItemPrice(){
		return itemPrice;
	}
	
	/**
	 * Overide of the toString method which will return a better formatted list of data for the item
	 */
	@Override
	public String toString(){
		return itemName + " : " + itemType + " : £" + String.format("%.2f", itemPrice);
	}
	

}

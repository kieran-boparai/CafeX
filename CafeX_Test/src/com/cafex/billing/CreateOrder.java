package com.cafex.billing;

import java.util.ArrayList;

/**
 * Used to build an order. This will hold a Menu object and will allow you to pull items from it and place into an Order
 * @author kieran.boparai
 *
 */
public class CreateOrder {
	
	private Menu menu;
	private ArrayList<MenuItem> order;
	
	/**
	 * Initialise the menu object and the ArrayList that will hold the items for the order
	 */
	public CreateOrder(){
		menu= new Menu();
		order = new ArrayList<MenuItem>();
	}
	
	/**
	 * Used to find MenuItem objects in Menu and then add them to order.
	 * If a matching MEnuItem is found then a message is displayed to confirm it has been added to the order
	 * If a matching MenuItem cannot be found a message will be displayed informing the user of this
	 * @param itemName - String - Item name for the MenuItem desired (as stored in the Menu)
	 * @return - String - Confirmation message to alert the user of the outcome
	 */
	public String addItemToOrder(String itemName){
		String output;
		MenuItem toAdd = menu.getItem(itemName);
		if(toAdd == null){
			output = itemName + " does not exist on the Menu. Please try again";
		} else {
			order.add(toAdd);
			output = toAdd.getItemName() + " has been added to the order";
		}
		return output;
	}
	
	
	// Main method will be used to run the CreateOrder class which will invoke other classes as required.
	public static void main(String[] args){
		CreateOrder order = new CreateOrder();
		System.out.println(order.addItemToOrder("cola"));
		System.out.println(order.addItemToOrder("cheese Sandwich"));
		System.out.println(order.addItemToOrder("coffee"));
		System.out.println(order.addItemToOrder("Steak Sandwich"));
	}
}
